package aeyama.world.blocks.units;

import arc.graphics.*;
import arc.math.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;

import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.storage.CoreBlock.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class ArmoryBlock extends Block {
    /** List of all the available armor. */
    public Seq<UnitType> armorChoices = new Seq<>(UnitType.class);
    /** For size offset in UI. */
    public int biggestTextLenght = 0;
    
    public ArmoryBlock(String name) {
        super(name);

        solid = true;
        update = true;
        configurable = true;
        sync = true;
        flags = EnumSet.of(BlockFlag.unitAssembler);

        config(Integer.class, ArmoryBuild::setIndex);
        config(UnitType.class, ArmoryBuild::setArmor);
    }

    @Override
    public void init() {
        super.init();

        for (UnitType armor : armorChoices)
            biggestTextLenght = Math.max(armor.localizedName.length(), biggestTextLenght);
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
                
                //Only check the side of the block
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

    @Override
    public void setStats() {
        super.setStats();
        
        stats.remove(Stat.unitType);
        stats.add(new Stat("armors", StatCat.function), t -> {
            t.row();
            for(UnitType armor : armorChoices) {
                if (armor.unlocked()) {
                    t.table(Styles.grayPanel, b -> {
                        b.image(armor.uiIcon).size(iconLarge).pad(10f).left().scaling(Scaling.fit);
                        b.table(i -> {
                            i.add(armor.localizedName).left().row();
                            i.add(armor.name).left().color(Color.lightGray);
                        });
                        b.button("?", Styles.flatBordert, () -> ui.content.show(armor)).size(40f).pad(10f).right().grow();
                    }).grow().pad(5f).row();
                } else {
                    t.table(Styles.grayPanel, b -> {
                        b.image(Icon.lock).size(iconLarge).pad(10f).left().scaling(Scaling.fit);
                        b.table(i -> {
                            i.add("???").left().color(Color.gray).row();
                            i.add("???").left().color(Color.lightGray);
                        });
                    }).grow().pad(5f).row();
                }
            }
        });
    }

    public class ArmoryBuild extends Building {
        //TODO get the core and save it
        /** The Core block next to this build. */
        public CoreBlock coreBlock = null;
        /** The Core build next to this build. */
        public CoreBuild coreBuild = null;
        /** The currently selected armor for this build. */
        public UnitType currentArmor = null;
        /** The index of the selected armor for this build. */
        public int index = 0;

        @Override
        public void buildConfiguration(Table table) {
            build(ArmoryBlock.this, this, table);
        }

        public void build(ArmoryBlock b, ArmoryBuild c, Table table) {
            for (UnitType armor : armorChoices) {
                int index = armorChoices.indexOf(armor);
                if (index != 0 && index % 2 == 0)
                    table.row();
                
                ImageButton button = new ImageButton(Styles.clearTogglei);
                if (armor.unlocked()) {
                    button.replaceImage(new Table(t -> {
                        t.image(armor.uiIcon).size(32f).scaling(Scaling.fit).center().row();
                        t.add(armor.localizedName).center().row();
                    }));
                    button.setSize(64f + biggestTextLenght);
                    button.changed(() -> c.configure(index));
                    button.update(() -> button.setChecked(index == getIndex()));
                } else { //TODO It's centered for some reason
                    table.add(new Table(t -> {
                        t.image(Icon.lock).size(32f).scaling(Scaling.fit).center();
                        t.setSize(64f + biggestTextLenght);
                        //? TODO Same style as the button but Table doesn't support style.
                    }));
                }
                table.add(button);
            }
            table.left();
        }

        @Override
        public void write(Writes write) {
            super.write(write);

            write.i(index);
            //Save the X and Y coordinates of the core build (and block).
            //TODO don't work cause no core
            if (coreBuild != null) {
                write.f(coreBuild.x);
                write.f(coreBuild.y);
                //Double because x would've been overwritten by y
                write.f(coreBuild.x);
                write.f(coreBuild.y);
            }
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);

            index = Mathf.clamp(read.i(), 0, armorChoices.size-1);
            //Get the core build using the saved coordinates.
            //TODO don't work cause no core saved
            coreBlock = (CoreBlock) Vars.world.tileWorld(read.f(), read.f()).block();
            coreBuild = (CoreBuild) Vars.world.tileWorld(read.f(), read.f()).build;
        }

        public void setArmor(UnitType armor) {
            if (armor != currentArmor) {
                currentArmor = armor;
                coreBlock.unitType = armor;
                coreBuild.requestSpawn(player);
            }
        }

        public UnitType getCurrentArmor() {
            return armorChoices.get(getIndex());
        }

        public void setIndex(int newIndex) {
            newIndex = Mathf.clamp(newIndex, 0, armorChoices.size-1);
            if (newIndex != index)
                index = newIndex;
        }

        public int getIndex() {
            return Mathf.clamp(index, 0, armorChoices.size-1);
        }
    }
}
