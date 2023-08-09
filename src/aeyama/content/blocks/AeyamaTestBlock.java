package aeyama.content.blocks;

import arc.struct.*;

import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;

import aeyama.content.*;
import aeyama.world.Choice;
import aeyama.world.Cost;
import aeyama.world.blocks.units.*;

public class AeyamaTestBlock {
    public static Block armory, weaponLocker;

    public static void load() {
        armory = new ArmoryBlock("armory-test") {{
            size = 2;

            armorChoices = Seq.with(
                new Choice(AeyamaUnits.sms, new Cost(10f)),
                new Choice(AeyamaUnits.assault, new Cost(
                    ItemStack.with(AeyamaItems.stoneBrick, 10, AeyamaItems.woodLumber, 10),
                    20f
                )),
                new Choice(AeyamaUnits.heavy, new Cost(
                    ItemStack.with(AeyamaItems.stoneBrick, 100, AeyamaItems.woodLumber, 100),
                    LiquidStack.with(AeyamaLiquids.water, 10),
                    30f
                )),
                new Choice(AeyamaUnits.scout, new Cost(
                    ItemStack.with(AeyamaItems.stoneBrick, 1000, AeyamaItems.woodLumber, 1000),
                    LiquidStack.with(AeyamaLiquids.water, 100),
                    10f,
                    40f
                )),
                new Choice(AeyamaUnits.colonist, new Cost(
                    ItemStack.with(AeyamaItems.stoneBrick, 10000, AeyamaItems.woodLumber, 10000),
                    LiquidStack.with(AeyamaLiquids.water, 1000),
                    100f,
                    10f,
                    50f
                ))
            );

            requirements(Category.effect, BuildVisibility.sandboxOnly, ItemStack.empty);
        }};

        // weaponLocker = new WeaponLockerBlock() {{

        // }};
    }
}