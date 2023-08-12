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
 !TODO Index/Choices seems to be null (might need to move getters to the Building class)
 TODO Complete "Stats" UI (build time, etc...)
 TODO Fix "Time" offset in "Stats" UI
 TODO Update bars depending on the current selected armor
 TODO Make it consume the necessary items/liquids/power/heat
 TODO Comment the function/variable
 */
public class ArmoryBlock extends Block {
    public Seq<Choice> armorChoices = new Seq<>(Choice.class);
    public UnitType currentArmor;
    private int index = 0;
    public Building core;
    public float overheatScale = 1f;
    public float maxEfficiency = 1f;

    public ArmoryBlock(String name) {
        super(name);

        solid = true;
        update = true;
        configurable = true;
        saveConfig = true;
        sync = true;
        flags = EnumSet.of(BlockFlag.unitAssembler);
        
        config(Integer.class, ArmoryBuild::setIndex);
    }

    @Override
    public void init() {
        hasItems = false;
        hasLiquids = false;
        hasPower = false;
        
        Cost cost = getCurrentCost();
        if (cost != null) { //! TODO Cost seems to be null
            hasItems |= cost.hasItems();
            hasLiquids |= cost.hasLiquids();
            hasPower |= cost.hasPower();
            itemCapacity = (int) Math.max(cost.maxItemAmount(), itemCapacity);
            liquidCapacity = Math.max(cost.maxLiquidAmount(), liquidCapacity);
        }
        consumesPower = hasPower;

        Log.info(hasItems); //? TODO False even tho should be true
        Log.info(hasLiquids); //? TODO False even tho should be true
        Log.info(hasPower); //? TODO False even tho should be true
        Log.info(itemCapacity); //? TODO Still default to 10 when it should be 10000
        Log.info(liquidCapacity); //? TODO Still default to 10f when it should be 10000f
        Log.info(consumesPower); //? TODO False even tho should be true
        
        super.init();
    }

    /** Act as a core extension */
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        Seq<Tile> tileAround = new Seq<>();

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
        return tileAround.contains(o -> (core = o.build) instanceof CoreBuild);
    }

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
                            //TODO fix weird space in front of the time
                            c.add(Core.bundle.format("stat.time", UI.formatTime(armorChoice.cost.time * 60f))).pad(0f);
                        }).left();
                    });
                    b.button("?", Styles.flatBordert, () -> ui.content.show(armorChoice.armor)).size(40f).pad(10f).right().grow().visible(() -> armorChoice.armor.unlockedNow());
                }).growX().pad(5f).row();
            }
        });
    }

    @Override
    public void setBars() {
        Cost currentCost = getCurrentCost();
        addBar("health", entity -> new Bar("stat.health", Pal.health, entity::healthf).blink(Color.white));

        if (hasItems && currentCost.hasItems())
            addBar("items", b -> new Bar(
                Core.bundle.format("bar.items", b.items.total()),
                Pal.items,
                () -> (float) (b.items.total() / itemCapacity)
            ));
        
        //TODO Support multi liquids?/Better implementation than copying vanilla
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

        Log.info("Bars init/updated");
    }

    public void updateBars() {
        Log.info("Updated Bars");
        barMap.clear();
        setBars();
    }


    public class ArmoryBuild extends Building implements HeatConsumer {
        public float[] sideHeat = new float[4];
        public float heat;

        @Override
        public void updateTile() {
            heat = calculateHeat(sideHeat);
            
            super.updateTile();
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            Cost currentCost = getCurrentCost();
            return (hasItems && currentCost.hasItems())
                && currentCost.itemsUnique.contains(item)
                && items.get(item) < itemCapacity;
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            Cost currentCost = getCurrentCost();
            return (hasLiquids && currentCost.hasLiquids())
                && currentCost.liquidsUnique.contains(liquid)
                && liquids.get(liquid) < liquidCapacity;
        }

        @Override
        public boolean shouldConsume() {
            return enabled && efficiency > 0;
        }

        @Override
        public void drawSelect() {
            super.drawSelect();
            if (core != null)
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
                });
                button.update(() -> button.setChecked(getCurrentChoice().armor == armorChoice.armor));

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
            write.f(core.x);
            write.f(core.y);

        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);

            index = Mathf.clamp(read.i(), 0, armorChoices.size-1);
            //Get the core with the saved coordinates
            core = Vars.world.buildWorld(read.f(), read.f());
        }

        public void setIndex(int newIndex) {
            newIndex = Mathf.clamp(newIndex, 0, armorChoices.size-1);
            Log.info("Set index to: " + newIndex);
            Log.info("Index: " + index);
            if (newIndex != index) {
                Log.info("Index not same: " + index);
                index = newIndex;
            }
            updateBars();
        }
    }

    public int getIndex() {
        //! VV spam console with blank VV
        // Log.info("Got index: " + Mathf.clamp(index, 0, armorChoices.size-1));
        return Mathf.clamp(index, 0, armorChoices.size-1);
    }

    public Choice getCurrentChoice() {
        return armorChoices.get(getIndex());
    }

    public Cost getCurrentCost() {
        return getCurrentChoice().cost;
    }
}
