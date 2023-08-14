package aeyama.content.blocks;

import arc.struct.*;

import mindustry.type.*;
import mindustry.world.*;

import aeyama.content.*;
import aeyama.world.blocks.units.*;

import multicraft.*;

public class AeyamaTestBlock {
    public static Block armory, weaponLocker, testMulticraft;

    public static void load() {
        testMulticraft = new MultiCrafter("test-multicraft") {{
            size = 2;

            selector = RecipeSelector.Transform;
            maxEfficiency = 1f;
            overheatScale = 1f;

            resolvedRecipes = Seq.with(
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.woodLumber, 1000)),
                        Seq.with(),
                        100f, 0f
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.woodLumber, 1000)),
                        Seq.with(),
                        1000f, 1000f
                    ), 600f
                )
            );

            requirements(Category.crafting, ItemStack.with(AeyamaItems.woodLumber, 1000));
        }};

        armory = new ArmoryBlock("armory-test") {{
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