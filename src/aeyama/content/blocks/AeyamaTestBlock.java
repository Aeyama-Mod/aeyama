package aeyama.content.blocks;

import arc.struct.*;

import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;

import aeyama.content.*;
import aeyama.world.blocks.storage.*;
import aeyama.world.blocks.units.*;

public class AeyamaTestBlock {
    public static Block testArmory, weaponLocker, testCore;

    public static void load() {
        testCore = new AeyamaCoreBlock("test-core-control") {{
            health = 2000;
            size = 3;
            itemCapacity = 3000;

            defaultUnit = AeyamaUnits.sms;

            requirements(Category.effect, BuildVisibility.shown, ItemStack.with(AeyamaItems.woodLumber, 1000, AeyamaItems.stoneBrick, 1000, AeyamaItems.iron, 500));
        }};

        testArmory = new ArmoryBlock("armory-test") {{
            size = 2;

            armorChoices = Seq.with(
                AeyamaUnits.sms,
                AeyamaUnits.assault,
                AeyamaUnits.heavy,
                AeyamaUnits.scout
            );

            requirements(Category.effect, ItemStack.with(AeyamaItems.steel, 10000));
        }};

        // weaponLocker = new WeaponLockerBlock() {{

        // }};
    }
}