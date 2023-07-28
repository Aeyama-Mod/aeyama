package aeyama.content.blocks;

import arc.graphics.*;
import arc.struct.*;

import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import aeyama.content.*;
import aeyama.world.blocks.storage.*;

import static mindustry.type.ItemStack.*;

public class AeyamaStorageBlocks {
    public static Block
    coreDropPod, coreFrontline, coreControl,

    /* Campaign */
    groundScanner,

    /* Storage */
    smallStockpile, stockpile, largeStockpile;

    public static void load () {
        groundScanner = new GenericCrafter("ground-scanner") {{
            health = 750;
            size = 4;

            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawSpikes(Color.valueOf("#FACF7F")) {{ rotateSpeed = .5f; }},
                new DrawPulseShape(false) {{
                    radiusScl = 2.5f;
                    square = false;
                    timeScl = 250f;
                }}
            );

            requirements(Category.effect, with(AeyamaItems.stoneBrick, 200, AeyamaItems.iron, 175));
        }};
        coreDropPod = new CoreBlock("core-drop-pod") {{
            health = 550;
            alwaysUnlocked = true;
            isFirstTier = true;
            itemCapacity = 500;

            unitType = AeyamaUnits.colonist;

            requirements(Category.effect, BuildVisibility.editorOnly, with());
        }};
        coreFrontline = new CoreBlock("core-frontline") {{
            scaledHealth = 470;
            size = 2;
            itemCapacity = 1500;

            unitType = AeyamaUnits.sms;

            requirements(Category.effect, BuildVisibility.shown, with(AeyamaItems.woodLumber, 300, AeyamaItems.stoneBrick, 600, AeyamaItems.rawIron, 150));
        }};
        coreControl = new MultiCoreBlock("core-control") {{
            health = 2000;
            size = 3;
            itemCapacity = 3000;

            unitTypes = Seq.with(
                AeyamaUnits.sms,
                AeyamaUnits.assault,
                AeyamaUnits.heavy,
                AeyamaUnits.scout
            );

            requirements(Category.effect, BuildVisibility.shown, with(AeyamaItems.woodLumber, 1000, AeyamaItems.stoneBrick, 1000, AeyamaItems.iron, 500));
        }};
        smallStockpile = new StorageBlock("stockpile-small") {{
            scaledHealth = 48;
            itemCapacity = 200;

            requirements(Category.effect, with(AeyamaItems.woodLumber, 40, AeyamaItems.stoneBrick, 60));
        }};
        stockpile = new StorageBlock("stockpile") {{
            scaledHealth = 420;
            size = 2;
            itemCapacity = 1000;

            requirements(Category.effect, with(AeyamaItems.woodLumber, 400, AeyamaItems.stoneBrick, 600, AeyamaItems.iron, 200));
        }};
        largeStockpile = new StorageBlock("stockpile-large") {{
            scaledHealth = 630;
            size = 3;
            itemCapacity = 2500;

            requirements(Category.effect, with(AeyamaItems.woodLumber, 750, AeyamaItems.stoneBrick, 900, AeyamaItems.steel, 200));
        }};
    }
}
