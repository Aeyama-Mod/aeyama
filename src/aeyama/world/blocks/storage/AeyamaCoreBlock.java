package aeyama.world.blocks.storage;

import arc.*;
import arc.graphics.*;
import arc.util.*;

import mindustry.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class AeyamaCoreBlock extends CoreBlock {
    /** The default unit of the core when no armor is equipped. */
    public UnitType defaultUnit;

    public AeyamaCoreBlock(String name) {
        super(name);
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.remove(Stat.buildTime);
        stats.remove(Stat.unitType);
        stats.add(Stat.unitType, table -> {
            table.row();
            table.table(Styles.grayPanel, b -> {
                b.image(defaultUnit.uiIcon).size(40).pad(10f).left().scaling(Scaling.fit);
                b.table(info -> {
                    info.add(defaultUnit.localizedName).left();
                    if(Core.settings.getBool("console")){
                        info.row();
                        info.add(defaultUnit.name).left().color(Color.lightGray);
                    }
                });
                b.button("?", Styles.flatBordert, () -> ui.content.show(defaultUnit)).size(40f).pad(10).right().grow().visible(() -> defaultUnit.unlockedNow());
            }).growX().pad(5).row();
        });
    }

    public static void playerSpawn(Tile tile, Player player) {
        if (player == null || tile == null || !(tile.build instanceof AeyamaCoreBuild core)) return;

        // If no armor was selected use the default unit to avoid null crash.
        UnitType spawnType = core.currentArmor != null ? core.currentArmor : ((AeyamaCoreBlock)core.block).defaultUnit;
        if (core.wasVisible) {
            Fx.spawn.at(core);
        }

        player.set(core);

        if (!net.client()) {
            Unit unit = spawnType.create(tile.team());
            unit.set(core);
            unit.rotation(90f);
            unit.impulse(0f, 3f);
            unit.spawnedByCore(true);
            unit.controller(player);
            unit.add();
        }

        if (state.isCampaign() && player == Vars.player) {
            spawnType.unlock();
        }
    }


    public class AeyamaCoreBuild extends CoreBuild {
        /** The current armor linked to this build */
        public UnitType currentArmor = null;

        @Override
        public void requestSpawn(Player player) {
            // If no armor was selected use the default unit to avoid null crash.
            UnitType unit = currentArmor != null ? currentArmor : defaultUnit;
            if (!unit.supportsEnv(state.rules.env)) return;

            if (Vars.net.server() || !Vars.net.active()) {
                AeyamaCoreBlock.playerSpawn(tile, player);
            }

            if (Vars.net.server()) {
                PlayerSpawnCallPacket packet = new PlayerSpawnCallPacket();
                packet.tile = tile;
                packet.player = player;
                Vars.net.send(packet, true);
            }        
        }
    }
}
