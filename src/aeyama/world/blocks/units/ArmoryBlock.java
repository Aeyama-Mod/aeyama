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
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.meta.*;

import aeyama.ui.*;
import aeyama.world.Choice;
import aeyama.world.Cost;

import static mindustry.Vars.*;

public class ArmoryBlock extends Block {
    public Seq<Choice> armorChoices = new Seq<>(Choice.class);
    public UnitType currentArmor;
    private int index = 0;

    public ArmoryBlock(String name) {
        super(name);

        solid = true;
        update = true;
        destructible = true;
        allowResupply = true;
        configurable = true;
        group = BlockGroup.units;
        flags = EnumSet.of(BlockFlag.unitAssembler);
        
        config(Integer.class, ArmoryBuild::setIndex);
    }

    @Override
    public void init() {
        super.init();

        setVariable();

        currentArmor = armorChoices.first().unit;
    }

    @Override
    public void setStats() {
        super.setStats();
    
        stats.remove(Stat.unitType);
        stats.add(new Stat("armors", StatCat.function), t -> {
            t.row();
            for(Choice armorChoice : armorChoices) {
                t.table(Styles.grayPanel, b -> {
                    b.image(armorChoice.unit.uiIcon).size(iconLarge).pad(10f).left().scaling(Scaling.fit);
                    b.table(i -> {
                        i.add(armorChoice.unit.localizedName).left().row();
                        i.add(armorChoice.unit.name).left().color(Color.lightGray).row();

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
                    b.button("?", Styles.flatBordert, () -> ui.content.show(armorChoice.unit)).size(40f).pad(10f).right().grow().visible(() -> armorChoice.unit.unlockedNow());
                }).growX().pad(5f).row();
            }
        });
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
        return tileAround.contains(o -> o.block() instanceof CoreBlock);
    }

    public class ArmoryBuild extends Building {

        public void build(ArmoryBlock b, ArmoryBuild c, Table table) {
            for (Choice armorChoice : armorChoices) {
                int index = armorChoices.indexOf(armorChoice);
                if (index != 0 && index % 2 == 0)
                    table.row();
                
                ImageButton button = new ImageButton(Styles.clearTogglei);
                button.replaceImage(new Table(t -> {
                    t.image(armorChoice.unit.uiIcon).size(32f).scaling(Scaling.fit).center().row();
                    t.add(armorChoice.unit.localizedName).center().row();
                    t.setSize(64f);
                    t.pack();
                }));
                button.changed(() -> c.configure(index));
                button.update(() -> button.setChecked(getCurrentChoice().unit == armorChoice.unit));

                table.add(button);
            }

            table.left().pack();
        }

        @Override
        public void buildConfiguration(Table table) {
            build(ArmoryBlock.this, this, table);
        }

        public void setIndex(int index) {
            index = Mathf.clamp(index, 0, armorChoices.size-1);
            if (index != ArmoryBlock.this.index)
                ArmoryBlock.this.index = index;
        }

        @Override
        public void write(Writes write) {
            super.write(write);

            write.i(index);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);

            index = Mathf.clamp(read.i(), 0, armorChoices.size-1);
        }
    }

    public void setVariable() {
        Cost cost = getCurrentChoice().cost;
        if (cost != null) {
            hasItems = acceptsItems = cost.hasItems();
            hasLiquids = cost.hasLiquids();
            hasPower = consumesPower = cost.hasPower();
        }
    }

    public int getIndex() {
        return Mathf.clamp(index, 0, armorChoices.size-1);
    }

    public Choice getCurrentChoice() {
        return armorChoices.get(getIndex());
    }
}
