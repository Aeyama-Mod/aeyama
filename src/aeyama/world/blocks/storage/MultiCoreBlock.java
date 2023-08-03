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
    public Seq<UnitType> unitTypes = new Seq<>(UnitType.class);
    public Seq<INeedANameClass> costs = new Seq<>(INeedANameClass.class);

    public MultiCoreBlock(String name) {
        super(name);

        configurable = true;

        config(UnitType.class, MultiCoreBuild::setCurrentUnit);
    }

    @Override
    public void init() {
        super.init();

        unitType = unitTypes.first();
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.remove(Stat.unitType);
        stats.add(new Stat("unitTypes", StatCat.function), t -> {
            t.row();
            for(UnitType unit : unitTypes) {
                int index = unitTypes.indexOf(unit);
                t.table(Styles.grayPanel, b -> {
                    b.image(unit.uiIcon).size(iconLarge).pad(10f).left().scaling(Scaling.fit);
                    b.table(i -> {
                        i.add(unit.localizedName).left().row();
                        i.add(unit.name).left().color(Color.lightGray).row();

                        INeedANameClass cost = costs.get(index);
                        i.table(c -> {
                            c.add(Core.bundle.get("stat.unitcosts"));
                            if (!cost.allEmtpy()) {
                                if (cost.hasItems())
                                    for (ItemStack items : cost.items)
                                        c.add(new ItemDisplay(items.item, items.amount, false)).padRight(5f);
                                if (cost.hasLiquids())
                                    for (LiquidStack liquids : cost.liquids)
                                        c.add(new ALiquidDisplay(liquids.liquid, liquids.amount)).padRight(5f);
                                if (cost.hasPower())
                                    c.add(new PowerDisplay((cost.power * 60f))).padRight(5f);
                                if (cost.hasHeat())
                                    c.add(new HeatDisplay(cost.heat)).padRight(5f);
                            } else c.add("[gray]" + Core.bundle.get("stat.none")).pad(0f);
                            c.row();
                            c.add(Core.bundle.format("stat.time", UI.formatTime(cost.time * 60f)));
                        }).left();
                    });
                    b.button("?", Styles.flatBordert, () -> ui.content.show(unit)).size(40f).pad(10f).right().grow().visible(() -> unit.unlockedNow());
                }).growX().pad(5f).row();
            }
        });
    }

    public class MultiCoreBuild extends CoreBuild {
        
        public void setCurrentUnit(UnitType unit) {
            if (unitType != unit)
                unitType = unit;
            
            requestSpawn(player);
        }

        public void build(MultiCoreBlock b, MultiCoreBuild c, Table table) {
            for (UnitType unit : unitTypes) {
                int index = unitTypes.indexOf(unit);
                if (index != 0 && index % 2 == 0)
                    table.row();
                
                ImageButton button = new ImageButton(Styles.clearTogglei);
                Table t = new Table();
                t.image(unit.uiIcon).size(40f).pad(10f).center().scaling(Scaling.fit).row();
                t.add(unit.localizedName).center();

                button.replaceImage(t);
                button.changed(() -> c.configure(unit));
                button.update(() -> button.setChecked(b.unitType == unit));

                table.add(button);
            }

            table.left().setSize(128f);
        }

        @Override
        public void buildConfiguration(Table table) {
            build(MultiCoreBlock.this, this, table);
        }
    }

    public class INeedANameClass {
        public Seq<ItemStack> items = new Seq<>(ItemStack.class);
        public Seq<LiquidStack> liquids = new Seq<>(LiquidStack.class);
        public float power = 0f;
        public float heat = 0f;
        public float time = 0f;

        public INeedANameClass(float time) {
            this(null, null, 0f, 0f, time);
        }
        
        public INeedANameClass(Seq<ItemStack> items, float time) {
            this(items, null, 0f, 0f, time);
        }

        public INeedANameClass(Seq<ItemStack> items, Seq<LiquidStack> liquids, float time) {
            this(items, liquids, 0f, 0f, time);
        }

        public INeedANameClass(Seq<ItemStack> items, Seq<LiquidStack> liquids, float power, float time) {
            this(items, liquids, power, 0f, time);
        }

        public INeedANameClass(Seq<ItemStack> items, Seq<LiquidStack> liquids, float power, float heat, float time) {
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
