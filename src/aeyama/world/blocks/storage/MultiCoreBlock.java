package aeyama.world.blocks.storage;

import arc.*;
import arc.graphics.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;

import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class MultiCoreBlock extends CoreBlock {
    public Seq<UnitType> unitTypes = new Seq<>();
    public Seq<ItemStack[]> unitCosts = new Seq<>();

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
                    b.image(unit.uiIcon).size(40f).pad(10f).left().scaling(Scaling.fit);
                    b.table(i -> {
                        i.add(unit.localizedName).left().row();;
                        i.add(unit.name).left().color(Color.lightGray).row();
                        if (unitCosts.get(index).length < 0){
                            i.add(Core.bundle.get("stat.unitcosts") + Core.bundle.get("none")).left();
                        } else {
                            i.add(Core.bundle.get("stat.unitcosts"));
                            for(ItemStack items : unitCosts.get(index)) {
                                i.add(new ItemDisplay(items.item, items.amount, false)).padRight(5f).left();
                            }
                        }
                    });
                    b.button("?", Styles.flatBordert, () -> ui.content.show(unit)).size(40f).pad(10).right().grow().visible(() -> unit.unlockedNow());
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
}
