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
import mindustry.graphics.*;
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
        return isNextToCore(this, tile, tile);
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
                } else { //TODO Is centered when it should be all to the left
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

    public @Nullable CoreBuild getCore(Team team, Tile tile) {
        var results = Vars.indexer.getFlagged(team, BlockFlag.core).<CoreBuild>as();

        return results.find(b -> isNextToCore(this, tile, b.tile));
    }

    public boolean isNextToCore(Block block, Tile tile, Tile coreTile) {
        Seq<Tile> tileAround = new Seq<>();

        /* Thanks to @_agzam_ */
        final int to = (block.size + 2) / 2;
        final int from = to + 1 - (block.size + 2);

        for (int ty = from; ty <= to; ty++) {
            for (int tx = from; tx <= to; tx++) {
                //Skip corners
                if ((ty == from || ty == to) && (tx == from ||tx == to))
                    continue;
                
                //Only check the side of the block
                if ((ty == from || ty == to || tx == from || tx == to)) {
                    int xx = coreTile.x + tx;
                    int yy = coreTile.y + ty;
                    tileAround.add(Vars.world.tile(xx, yy));
                }
            }
        }

        //Not done keep picking random core I'm done I cn't anymore like please it's been 8 hours I work on this fucking thing
        return tileAround.contains(o -> o.build instanceof CoreBuild);
    }

    public class ArmoryBuild extends Building {
        /** The Core block next to this build. */
        public CoreBlock coreBlock = null;
        /** The Core build next to this build. */
        public CoreBuild coreBuild = null;
        /** The currently selected armor for this build. */
        public UnitType currentArmor = null;
        /** The index of the selected armor for this build. */
        public int index = 0;

        public int lastChange = -2;

        public ArmoryBuild() {
            //To avoid any null error, it will always be the first change because index is 0.
            currentArmor = getCurrentArmor();
        }

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
                    button.changed(() -> {
                        c.configure(index);
                        c.configure(armor);
                    });
                    button.update(() -> button.setChecked(index == getIndex()));
                } else {
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
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);

            index = Mathf.clamp(read.i(), 0, armorChoices.size-1);            
        }

        @Override
        public void drawSelect() {
            Drawf.selected(coreBuild, Pal.accent);
        }

        @Override
        public void updateTile() {
            if (lastChange != world.tileChanges) {
                lastChange = world.tileChanges;
                findCore();
            }
        }

        public void findCore() {
            coreBuild = getCore(team, this.tile);
            Log.info(this.tile);
            if (coreBuild != null)
                coreBlock = (CoreBlock) coreBuild.block;
        }

        public void setArmor(UnitType armor) {
            if (armor != currentArmor) {
                currentArmor = armor;
                if (coreBlock != null && coreBuild != null) {
                    coreBlock.unitType = armor;
                    coreBuild.requestSpawn(player);
                }
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
