package aeyama.content;

import mindustry.world.*;
import mindustry.world.blocks.environment.*;

public class AeyamaBlocks {
    public static Block
    floorStoneSlate, floorStoneGranite, floorStoneLimestone, floorStoneSandstone,
    floorStoneMarble,

    wallWoodTree;

    public static void load() {
        //#region Environment
        floorStoneSlate = new Floor("floor-stone-slate") {{
            itemDrop = AeyamaItems.stone;
        }};
        floorStoneGranite = new Floor("floor-stone-granite") {{
            itemDrop = AeyamaItems.stone;
        }};
        floorStoneLimestone = new Floor("floor-stone-limestone") {{
            itemDrop = AeyamaItems.stone;
        }};
        floorStoneSandstone = new Floor("floor-stone-sandstone") {{
            itemDrop = AeyamaItems.stone;
        }};
        floorStoneMarble = new Floor("floor-stone-marble") {{
            itemDrop = AeyamaItems.stone;
        }};

        wallWoodTree = new StaticWall("wall-wood-tree") {{
            itemDrop = AeyamaItems.wood;
        }};
        //#endregion
    }
}
