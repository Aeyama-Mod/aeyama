package aeyama.world.blocks.units;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;

import mindustry.*;
import mindustry.core.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.storage.CoreBlock.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import aeyama.ui.*;
import aeyama.world.Choice;
import aeyama.world.Cost;

import static mindustry.Vars.*;

/*
 !TODO Fix Bars UI not updating like usual
 ?TODO Fix core saving issue?
 TODO Fix "Time" offset in "Stats" UI
 TODO Make it consume the necessary items/liquids/power/heat
 TODO Comment the function/variable
 */
public class ArmoryBlock extends Block {
    /** List of all the available armor and their cost. */
    public Seq<Choice> armorChoices = new Seq<>(Choice.class);
    public Choice currentChoice; //Used a little outside of the inner Building class
    public float overheatScale = 1f; //Used for heat, no idea how it works yet
    public float maxEfficiency = 1f; //Used for heat, no idea how ti works yet
    private int index = 0; //? Global for all building or local and unique for all building?
    public Seq<Tile> tileAround = new Seq<>(); //Only used to save the core to the building ,not the best way

    public ArmoryBlock(String name) {
        super(name);

        solid = true;
        update = true;
        configurable = true;
        saveConfig = true;
        sync = true;
        flags = EnumSet.of(BlockFlag.unitAssembler);
        
        config(Integer.class, ArmoryBuild::setIndex);
        config(Choice.class, ArmoryBuild::setCurrentChoice);
    }

    @Override
    public void init() {
        currentChoice = armorChoices.get(index);
        hasItems = false;
        hasLiquids = false;
        hasPower = false;
        
        //TODO make it dynamic?
        for (Choice choice : armorChoices) {
            Cost cost = choice.cost;
            if (cost != null) {
                hasItems |= cost.hasItems();
                hasLiquids |= cost.hasLiquids();
                hasPower |= cost.hasPower();
                itemCapacity = (int) Math.max(cost.maxItemAmount(), itemCapacity);
                liquidCapacity = Math.max(cost.maxLiquidAmount(), liquidCapacity);
            }
        }
        consumesPower = hasPower;
        
        super.init();
    }

    /** Act as a core extension */
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        tileAround = new Seq<>();
        
        /* Thanks to @_agzam_ */
        final int to = (this.size + 2) / 2;
        final int from = to + 1 - (this.size + 2);
        for (int ty = from; ty <= to; ty++) {
            for (int tx = from; tx <= to; tx++) {
                //Skip corners
                if ((ty == from || ty == to) && (tx == from ||tx == to))
                    continue;
                
                if ((ty == from || ty == to || tx == from || tx == to)) {
                    int xx = tile.x + tx;
                    int yy = tile.y + ty;
                    tileAround.add(Vars.world.tile(xx, yy));
                }
            }
        }
        
        // Can be placed only if the block next to it is a Core
        return tileAround.contains(o -> o.build instanceof CoreBuild);
    }

    //? TODO Remove the vanilla implementation for custome one?
    @Override
    public void setStats() {
        super.setStats();
        
        stats.remove(Stat.unitType);
        stats.add(new Stat("armors", StatCat.function), t -> {
            t.row();
            for(Choice armorChoice : armorChoices) {
                t.table(Styles.grayPanel, b -> {
                    b.image(armorChoice.armor.uiIcon).size(iconLarge).pad(10f).left().scaling(Scaling.fit);
                    b.table(i -> {
                        i.add(armorChoice.armor.localizedName).left().row();
                        i.add(armorChoice.armor.name).left().color(Color.lightGray).row();

                        i.table(c -> {
                            c.add(Core.bundle.get("stat.armorcost"));
                            if (armorChoice.cost != null && !armorChoice.cost.allEmtpy()) {
                                if (armorChoice.cost.hasItems())
                                    for (ItemStack items : armorChoice.cost.items)
                                        c.add(new ItemDisplay(items.item, items.amount, false)).padRight(5f);
                                if (armorChoice.cost.hasLiquids())
                                    for (LiquidStack liquids : armorChoice.cost.liquids)
                                        c.add(new ALiquidDisplay(liquids.liquid, liquids.amount)).padRight(5f);
                                if (armorChoice.cost.hasPower())
                                    c.add(new PowerDisplay((armorChoice.cost.power * 60f))).padRight(5f);
                                if (armorChoice.cost.hasHeat())
                                    c.add(new HeatDisplay(armorChoice.cost.heat)).padRight(5f);
                            } else c.add("[gray]" + Core.bundle.get("stat.none")).pad(0f);
                            c.row();
                            //? TODO fix the weird 2 spaces in front of the time, pad(0) didn't work
                            c.add(Core.bundle.format("stat.time", UI.formatTime(armorChoice.cost.time * 60f))).pad(0f);
                        }).left();
                    });
                    b.button("?", Styles.flatBordert, () -> ui.content.show(armorChoice.armor)).size(40f).pad(10f).right().grow().visible(() -> armorChoice.armor.unlockedNow());
                }).growX().pad(5f).row();
            }
        });
    }

    //! TODO Don't update unless switch to another block UI for some reason
    @Override
    public void setBars() {
        Cost currentCost = currentChoice.cost;
        addBar("health", b -> new Bar(
            "stat.health",
            Pal.health,
            b::healthf
        ).blink(Color.white));

        if (hasItems && currentCost.hasItems())
            addBar("items", b -> new Bar(
                Core.bundle.format("bar.items", b.items.total()),
                Pal.items,
                () -> (float) (b.items.total() / itemCapacity)
            ));
        
        //TODO Support multi liquids?/Better implementation than copying vanilla, and it's kinda ugly
        if (hasLiquids && currentCost.hasLiquids()) {
            boolean added = false;

            //add bars for *specific* consumed liquids
            for(var consl : consumers) {
                if(consl instanceof ConsumeLiquid liq) {
                    added = true;
                    addLiquidBar(liq.liquid);
                } else if(consl instanceof ConsumeLiquids multi) {
                    added = true;
                    for(var stack : multi.liquids) {
                        addLiquidBar(stack.liquid);
                    }
                }
            }
            //nothing was added, so it's safe to add a dynamic liquid bar (probably?)
            if(!added){
                addLiquidBar(build -> build.liquids.current());
            }
        }
        
        //TODO Doesn't accept power for some reason so it's always at 0
        if (hasPower && currentCost.hasPower())
            addBar("power", (ArmoryBuild b) -> new Bar(
                "bar.power",
                Pal.powerBar,
                () -> b.efficiency
            ));
        
        if (currentCost.hasHeat())
            addBar("heat", (ArmoryBuild b) -> new Bar(
                Core.bundle.format("bar.heatpercent", (int)(b.heat + 0.01f), (int)(b.efficiencyScale() * 100 + 0.01f)),
                Pal.lightOrange,
                () -> b.heat / currentCost.heat
            ));
        
        addBar("progress", b -> new Bar(
            "bar.loadprogress",
            Pal.accent,
            b::progress
        ));
    }

    //? Maybe not the best way to update it
    public void updateBars() {
        barMap.clear();
        setBars();
    }

    /** This block doesn't output any item */
    @Override
    public boolean outputsItems() {
        return false;
    }


    public class ArmoryBuild extends Building implements HeatConsumer {
        public float[] sideHeat = new float[4];
        public float heat;
        public Building core;
        //? Do I save the index globally for all block or locally to each building?

        public ArmoryBuild() {
            //? TODO Only way to save the core to the build, but fix nothing
            tileAround.contains(o -> (core = o.build instanceof CoreBuild ? o.build : null) instanceof CoreBuild);
            tileAround = null; //Free the Seq
        }

        @Override
        public void updateTile() {
            heat = calculateHeat(sideHeat);

            //TODO lot of things
            
            super.updateTile();
        }

        /** Only accept items if it's needed and have enough space */
        @Override
        public boolean acceptItem(Building source, Item item) {
            Cost currentCost = getCurrentCost();
            return (hasItems && currentCost.hasItems())
                && currentCost.itemsUnique.contains(item)
                && items.get(item) < currentCost.itemCapacity;
        }

        /** Only accept liquids if it's needed and have enough space */
        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            Cost currentCost = getCurrentCost();
            return (hasLiquids && currentCost.hasLiquids())
                && currentCost.liquidsUnique.contains(liquid)
                && liquids.get(liquid) < currentCost.liquidCapacity;
        }

        @Override
        public boolean shouldConsume() {
            //TODO Lot of things too
            return enabled && efficiency > 0;
        }

        @Override
        public void drawSelect() {
            super.drawSelect();
            if (core != null) //! TODO Core become null, can be easily seen when the core is no longer highlighted when the block is selected
                Drawf.selected(core, Pal.accent);
        }

        @Override
        public void buildConfiguration(Table table) {
            build(ArmoryBlock.this, this, table);
        }

        public void build(ArmoryBlock b, ArmoryBuild c, Table table) {
            for (Choice armorChoice : armorChoices) {
                int index = armorChoices.indexOf(armorChoice);
                if (index != 0 && index % 2 == 0)
                    table.row();
                
                ImageButton button = new ImageButton(Styles.clearTogglei);
                button.replaceImage(new Table(t -> {
                    t.image(armorChoice.armor.uiIcon).size(32f).scaling(Scaling.fit).center().row();
                    t.add(armorChoice.armor.localizedName).center().row();
                    t.setSize(64f);
                    t.pack();
                }));
                button.changed(() -> {
                    c.configure(index);
                    c.configure(armorChoice);
                });
                button.update(() -> {
                    button.setChecked(getCurrentChoice().armor == armorChoice.armor);
                    updateBars(); //! TODO Static, not updating like usual for no reason
                });

                table.add(button);
            }
            table.left().pack();
        }

        /** As {@linkplain HeatConsumer} only for visual effects */
        @Override
        public float[] sideHeat() {
            return sideHeat;
        }

        /** As {@linkplain HeatConsumer} only for visual effects */
        @Override
        public float heatRequirement() {
            return getCurrentCost().heat;
        }

        public float warmupTarget() {
            Cost currentCost = getCurrentCost();
            if (currentCost.hasHeat())
                return Mathf.clamp(heat / currentCost.heat);
            else return 1f;
        }

        @Override
        public float efficiencyScale() {
            Cost currentCost = getCurrentCost();
            if (currentCost.hasHeat()) {
                float heatRequirement = currentCost.heat;
                float over = Math.max(heat - heatRequirement, 0f);
                return Math.min(Mathf.clamp(heat / heatRequirement) + over / heatRequirement * overheatScale, maxEfficiency);
            } else return 1f;
        }

        @Override
        public void write(Writes write) {
            super.write(write);

            write.i(index);            
            //Save the core coordinates
            if (core != null) write.f(core.x); //! TODO Core become null at some point, can't save and might corrupt save (if the block is built and core becoma null)
            if (core != null) write.f(core.y);

        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);

            index = Mathf.clamp(read.i(), 0, armorChoices.size-1);
            //Get the core with the saved coordinates
            core = Vars.world.buildWorld(read.f(), read.f()); //! TODO well can't read something that is not saved
        }

        //? Might not be the best implementation, I do I use it
        public void setCore(Building newCore) {
            if (core != newCore)
                core = newCore;
        }

        public void setCurrentChoice(Choice choice) {
            itemCapacity = choice.cost.itemCapacity;
            liquidCapacity = choice.cost.liquidCapacity;
            if (currentChoice != choice)
                currentChoice = choice;
        }

        public void setIndex(int newIndex) {
            newIndex = Mathf.clamp(newIndex, 0, armorChoices.size-1);
            if (newIndex != index)
                index = newIndex;
        }

        public int getIndex() {
            return Mathf.clamp(index, 0, armorChoices.size-1);
        }

        public Choice getCurrentChoice() {
            return armorChoices.get(getIndex());
        }

        public Cost getCurrentCost() {
            return getCurrentChoice().cost;
        }
    }
}
