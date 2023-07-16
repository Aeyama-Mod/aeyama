package aeyama.content.blocks;

import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.distribution.*;

import aeyama.content.*;

import static mindustry.type.ItemStack.*;

public class AeyamaDistributionBlocks {
    public static Block
    woodConveyor, woodRouter, woodJunction,
    ironConveyor, ironRouter, ironJunction, ironBridge,
    steelConveyor;

    public static void load() {
        woodConveyor = new Conveyor("wood-conveyor") {{
            health = 8;
            speed = 0.03f;
            displayedSpeed = speed * 140;

            requirements(Category.distribution, with(AeyamaItems.woodLumber, 2));
        }};
        woodRouter = new Router("wood-router") {{
            health = 20;

            requirements(Category.distribution, with(AeyamaItems.woodLumber, 6, AeyamaItems.stone, 2));
        }};
        woodJunction = new Junction("wood-junction") {{
            health = 15;

            requirements(Category.distribution, with(AeyamaItems.woodLumber, 4, AeyamaItems.stoneBrick, 4));
        }};
        ironConveyor = new Conveyor("iron-conveyor") {{
            // scaledHealth = 
            speed = 0.06f;
            displayedSpeed = speed * 140;

            requirements(Category.distribution, with(AeyamaItems.iron, 2, AeyamaItems.woodLumber, 2)); //TODO
        }};
        ironRouter = new Router("iron-router") {{
            // scaledHealth =
            speed = 16f;

            requirements(Category.distribution, with(AeyamaItems.iron, 6)); //TODO
        }};
        ironJunction = new Junction("iron-junction") {{
            // scaledHealth =

            requirements(Category.distribution, with(AeyamaItems.iron, 4)); //TODO
        }};
        ironBridge = new ItemBridge("iron-bridge") {{
            // scaledHealth = 
            range = 3;

            requirements(Category.distribution, with(AeyamaItems.iron, 8));
        }};
        steelConveyor = new Conveyor("steel-conveyor") {{
            // scaledHealth = 
            speed = 0.12f;
            displayedSpeed = speed * 140;

            requirements(Category.distribution, with(AeyamaItems.iron, 2, AeyamaItems.steel, 2)); //TODO
        }};
    }
}
