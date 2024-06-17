package aeyama.content;

import mindustry.world.*;
import mindustry.world.blocks.environment.*;

public class AeyamaBlocks {
    public static Block
    stoneSlateFloor, stoneGraniteFloor, stoneLimestoneFloor, stoneSandstoneFloor,
    stoneMarbleFloor,

    woodTreeWall;

    public static void load() {
        //#region Environment
        stoneSlateFloor = new Floor("floor-stone-slate") {{
            itemDrop = AeyamaItems.stone;
        }};
        stoneGraniteFloor = new Floor("floor-stone-granite") {{
            itemDrop = AeyamaItems.stone;
        }};
        stoneLimestoneFloor = new Floor("floor-stone-limestone") {{
            itemDrop = AeyamaItems.stone;
        }};
        stoneSandstoneFloor = new Floor("floor-stone-sandstone") {{
            itemDrop = AeyamaItems.stone;
        }};
        stoneMarbleFloor = new Floor("floor-stone-marble") {{
            itemDrop = AeyamaItems.stone;
        }};

        woodTreeWall = new StaticWall("wall-wood-tree") {{
            itemDrop = AeyamaItems.wood;
        }};
        //#endregion
    }
}
