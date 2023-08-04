package aeyama.world.blocks.storage;

import arc.*;
import arc.graphics.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;

import mindustry.core.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.meta.*;

import aeyama.ui.*;

import static mindustry.Vars.*;

public class MultiCoreBlock extends CoreBlock {
    public Seq<UnitChoice> unitChoices = new Seq<>(UnitChoice.class);
    public UnitType currentUnit;

    public MultiCoreBlock(String name) {
        super(name);

        configurable = true;

        config(UnitType.class, MultiCoreBuild::setCurrentUnit);
    }

    @Override
    public void init() {
        super.init();

        unitType = currentUnit = unitChoices.first().unit;
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.remove(Stat.unitType);
        stats.add(new Stat("unitTypes", StatCat.function), t -> {
            t.row();
            for(UnitChoice unitChoice : unitChoices) {
                t.table(Styles.grayPanel, b -> {
                    b.image(unitChoice.unit.uiIcon).size(iconLarge).pad(10f).left().scaling(Scaling.fit);
                    b.table(i -> {
                        i.add(unitChoice.unit.localizedName).left().row();
                        i.add(unitChoice.unit.name).left().color(Color.lightGray).row();

                        i.table(c -> {
                            c.add(Core.bundle.get("stat.unitcosts"));
                            if (unitChoice.hasCost() && !unitChoice.cost.allEmtpy()) {
                                if (unitChoice.cost.hasItems())
                                    for (ItemStack items : unitChoice.cost.items)
                                        c.add(new ItemDisplay(items.item, items.amount, false)).padRight(5f);
                                if (unitChoice.cost.hasLiquids())
                                    for (LiquidStack liquids : unitChoice.cost.liquids)
                                        c.add(new ALiquidDisplay(liquids.liquid, liquids.amount)).padRight(5f);
                                if (unitChoice.cost.hasPower())
                                    c.add(new PowerDisplay((unitChoice.cost.power * 60f))).padRight(5f);
                                if (unitChoice.cost.hasHeat())
                                    c.add(new HeatDisplay(unitChoice.cost.heat)).padRight(5f);
                            } else c.add("[gray]" + Core.bundle.get("stat.none")).pad(0f);
                            c.row();
                            c.add(Core.bundle.format("stat.time", UI.formatTime(unitChoice.cost.time * 60f)));
                        }).left();
                    });
                    b.button("?", Styles.flatBordert, () -> ui.content.show(unitChoice.unit)).size(40f).pad(10f).right().grow().visible(() -> unitChoice.unit.unlockedNow());
                }).growX().pad(5f).row();
            }
        });
    }

    @Override
    public void setBars() {
        super.setBars();
    }

    public class MultiCoreBuild extends CoreBuild {
        
        public void setCurrentUnit(UnitType unit) {
            if (unitType != unit)
                unitType = currentUnit = unit;
            
            requestSpawn(player);
        }

        public void build(MultiCoreBlock b, MultiCoreBuild c, Table table) {
            for (UnitChoice unitChoice : unitChoices) {
                int index = unitChoices.indexOf(unitChoice);
                if (index != 0 && index % 2 == 0)
                    table.row();
                
                ImageButton button = new ImageButton(Styles.clearTogglei);
                Table t = new Table();
                t.image(unitChoice.unit.uiIcon).size(32f).pad(10f).center().scaling(Scaling.fit).row();
                t.add(unitChoice.unit.localizedName).center().row();

                // INeedANameClass cost = costs.get(index);
                // t.add(new Table(i -> {
                //     if (!cost.allEmtpy()) {
                //         if (cost.hasItems())
                //             for (ItemStack items : cost.items)
                //                 i.add(new ItemDisplay(items.item, items.amount, false)).padRight(2.5f);
                //         if (cost.hasLiquids())
                //             for (LiquidStack liquids : cost.liquids)
                //                 i.add(new ALiquidDisplay(liquids.liquid, liquids.amount)).padRight(2.5f);
                //         if (cost.hasPower())
                //             i.add(new PowerDisplay((cost.power * 60f))).padRight(2.5f);
                //         if (cost.hasHeat())
                //             i.add(new HeatDisplay(cost.heat)).padRight(2.5f);
                //     } else i.add("[gray]" + Core.bundle.get("stat.none")).pad(0f);
                //     i.row();
                // }).center());

                button.replaceImage(t);
                button.changed(() -> c.configure(unitChoice.unit));
                button.update(() -> button.setChecked(b.unitType == unitChoice.unit));

                table.add(button);
            }

            table.left().setSize(128f);
        }

        @Override
        public void buildConfiguration(Table table) {
            build(MultiCoreBlock.this, this, table);
        }
    }

    public class UnitChoice {
        public UnitType unit;
        public UnitCost cost;

        public UnitChoice(UnitType unit) {
            this(unit, null);
        }

        public UnitChoice(UnitType unit, UnitCost cost) {
            this.unit = unit;
            this.cost = cost;
        }

        public boolean hasCost() {
            return cost != null;
        }
    }

    public class UnitCost {
        public Seq<ItemStack> items = new Seq<>(ItemStack.class);
        public Seq<LiquidStack> liquids = new Seq<>(LiquidStack.class);
        public float power = 0f;
        public float heat = 0f;
        public float time = 0f;

        public UnitCost(float time) {
            this(null, null, 0f, 0f, time);
        }
        
        public UnitCost(Seq<ItemStack> items, float time) {
            this(items, null, 0f, 0f, time);
        }

        public UnitCost(Seq<ItemStack> items, Seq<LiquidStack> liquids, float time) {
            this(items, liquids, 0f, 0f, time);
        }

        public UnitCost(Seq<ItemStack> items, Seq<LiquidStack> liquids, float power, float time) {
            this(items, liquids, power, 0f, time);
        }

        public UnitCost(Seq<ItemStack> items, Seq<LiquidStack> liquids, float power, float heat, float time) {
            this.items = items;
            this.liquids = liquids;
            this.power = power;
            this.heat = heat;
            this.time = time;
        }

        public boolean hasItems() {
            return items != null;
        }

        public boolean hasLiquids() {
            return liquids != null;
        }

        public boolean hasPower() {
            return power > 0f;
        }

        public boolean hasHeat() {
            return heat > 0f;
        }

        public boolean allEmtpy() {
            return !hasItems() && !hasLiquids() && !hasPower() && !hasHeat();
        }
    }
}
