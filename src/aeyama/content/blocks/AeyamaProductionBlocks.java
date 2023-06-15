package aeyama.content.blocks;

import arc.graphics.*;

import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import aeyama.content.*;

import static mindustry.type.ItemStack.*;

public class AeyamaProductionBlocks {
    public static Block
    /* Drills */
    woodHarvester, stoneMiner, ironMiner, copperMiner, zincMiner,

    /* Factory */
    shredder, woodDryer, burner, brickMaker, ammunitionPress,
    smelterIron, smelterCopper, smelterZinc, foundryBrass,
    foundrySteel, researchStation, researchLab;

    public static void load() {
        /* Drills */
        woodHarvester = new WallCrafter("wood-harvester") {{
            scaledHealth = 20f;
            size = 2;

            drillTime = 120f;
            output = AeyamaItems.woodLumber;
            attribute = Attribute.get("wood");

            requirements(Category.production, with(AeyamaItems.woodLumber, 20));
        }};
        stoneMiner = new AttributeCrafter("stone-miner") {{
            scaledHealth = 35f;
            size = 2;
            
            craftTime = 140f;
            baseEfficiency = 0f;
            outputItems = with(AeyamaItems.stone, 2);
            drawer = new DrawMulti(
                 new DrawDefault(),
                 new DrawRegion("-rotator", 1f),
                 new DrawRegion("-top")
            );
            attribute = Attribute.get("stone");

            requirements(Category.production, with(AeyamaItems.woodLumber, 35));
        }};
        ironMiner = new AttributeCrafter("iron-miner") {{
            scaledHealth = 120;
            size = 2;

            craftTime = 180f;
            baseEfficiency = 0f;
            outputItems = with(AeyamaItems.rawIron, 2);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawRegion("-rotator", 1f),
                new DrawRegion("-top")
            );
            attribute = Attribute.get("iron");

            requirements(Category.production, with(AeyamaItems.woodLumber, 20, AeyamaItems.stoneBrick, 50));
        }};
        copperMiner = new AttributeCrafter("copper-miner") {{
            scaledHealth = 50f;
            size = 2;

            craftTime = 180f;
            baseEfficiency = 0f;
            outputItems = with(AeyamaItems.rawCopper, 2);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawRegion("-rotator", 1f),
                new DrawRegion("-top")
            );
            attribute = Attribute.get("copper");

            requirements(Category.production, with(AeyamaItems.iron, 20, AeyamaItems.stoneBrick, 50));
        }};
        zincMiner = new AttributeCrafter("zinc-miner") {{
            scaledHealth = 50f;
            size = 2;

            craftTime = 180f;
            baseEfficiency = 0f;
            outputItems = with(AeyamaItems.rawZinc, 2);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawRegion("-rotator", 1f),
                new DrawRegion("-top")
            );
            attribute = Attribute.get("zinc");

            requirements(Category.production, with(AeyamaItems.iron, 20, AeyamaItems.stoneBrick, 50));
        }};
        
        /* Factory */
        shredder = new GenericCrafter("shredder") {{
            scaledHealth = 278;
            size = 2;

            craftTime = 180f;
            consumeItem(AeyamaItems.woodLumberDry, 10);
            outputItems = with(AeyamaItems.woodShreds, 2);

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 125, AeyamaItems.stoneBrick, 50, AeyamaItems.rawIron, 35));
        }};
        woodDryer = new GenericCrafter("wood-dryer") {{
            scaledHealth = 200;
            size = 2;

            craftTime = 600f;
            consumeItem(AeyamaItems.woodLumber, 10);
            outputItems = with(AeyamaItems.woodLumberDry, 10);

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 100, AeyamaItems.stoneBrick, 50)); //TODO
        }};
        burner = new HeatProducer("burner") {{
            health = 360;

            craftTime = 600f;
            consume(new ConsumeItemFlammable() {{ minFlammability = .8f; }});
            heatOutput = 50f;
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame(Color.valueOf("#FF6230")) {{
                    flameRadius = 1f;
                    flameRadiusIn = .7f;
                    flameRadiusScl = 7f;
                    flameRadiusMag = .8f;
                    flameRadiusInMag = .5f;
                }}
            );

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 50, AeyamaItems.stoneBrick, 100));
        }};
        brickMaker = new GenericCrafter("brick-maker") {{
            scaledHealth = 105f;
            size = 2;

            craftTime = 60f;
            consumeItem(AeyamaItems.stone, 1);
            outputItems = with(AeyamaItems.stoneBrick, 2);

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 75, AeyamaItems.stone, 30));
        }};
        ammunitionPress = new CoreBlock("ammunition-press") {{
            health = 550;

            itemCapacity = 550;
            unitType = AeyamaUnitTypes.sms;

            requirements(Category.effect, BuildVisibility.editorOnly, with());
        }};
        smelterIron = new HeatCrafter("smelter-iron") {{
            // scaledHealth =
            size = 2;

            craftTime = 300f;
            maxEfficiency = 2f;
            overheatScale = .25f;
            heatRequirement = 80f;
            consumeItem(AeyamaItems.rawIron, 3);
            outputItems = with(AeyamaItems.iron, 1);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame(Color.valueOf("#FF9940"))
            );

            requirements(Category.crafting, with(AeyamaItems.stoneBrick, 100, AeyamaItems.woodLumber, 25, AeyamaItems.rawIron, 25));
        }};
        smelterCopper = new HeatCrafter("smelter-copper") {{
            // scaledHealth =
            size = 2;

            craftTime = 300f;
            maxEfficiency = 2f;
            overheatScale = .25f;
            heatRequirement = 80f;
            consumeItem(AeyamaItems.rawCopper, 3);
            outputItems = with(AeyamaItems.copper, 1);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame(Color.valueOf("#FF9940"))
            );

            requirements(Category.crafting, with(AeyamaItems.stoneBrick, 100, AeyamaItems.woodLumber, 25, AeyamaItems.rawCopper, 25));
        }};
        smelterZinc = new HeatCrafter("smelter-zinc") {{
            // scaledHealth =
            size = 2;

            craftTime = 300f;
            maxEfficiency = 2f;
            overheatScale = .25f;
            heatRequirement = 80f;
            consumeItem(AeyamaItems.rawZinc, 3);
            outputItems = with(AeyamaItems.zinc, 1);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame(Color.valueOf("#FF9940"))
            );

            requirements(Category.crafting, with(AeyamaItems.stoneBrick, 100, AeyamaItems.woodLumber, 25, AeyamaItems.rawZinc, 25));
        }};
        foundryBrass = new HeatCrafter("foundry-brass") {{
            // scaledHealth =
            size = 3;

            craftTime = 150f;
            maxEfficiency = 2f;
            overheatScale = .25f;
            heatRequirement = 80f;
            consumeItems(with(AeyamaItems.copper, 2, AeyamaItems.zinc, 2));
            outputItems = with(AeyamaItems.brass, 1);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawGlowRegion("-glow")
            );

            requirements(Category.crafting, with(AeyamaItems.stoneBrick, 150, AeyamaItems.woodLumber, 25, AeyamaItems.iron, 50));
        }};
        foundrySteel = new HeatCrafter("foundry-steel") {{
            // scaledHealth =
            size = 3;

            craftTime = 150f;
            maxEfficiency = 2f;
            overheatScale = .25f;
            heatRequirement = 240f;
            consumeItems(with(AeyamaItems.iron, 2, AeyamaItems.carbon, 4));
            outputItems = with(AeyamaItems.steel, 1);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawGlowRegion("-glow")
            );

            requirements(Category.crafting, with(AeyamaItems.stoneBrick, 150, AeyamaItems.woodLumber, 25, AeyamaItems.iron, 100));
        }};
        researchStation = new GenericCrafter("research-station") {{
            scaledHealth = 263f;
            size = 3;

            craftTime = 3600f;
            outputItems = with(AeyamaItems.blueprint, 1);

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 80, AeyamaItems.stoneBrick, 120, AeyamaItems.rawIron, 50));
        }};
        researchLab = new GenericCrafter("research-lab") {{
            scaledHealth = 263f;
            size = 4;

            craftTime = 7200f;
            outputItems = with(AeyamaItems.advancedBlueprint, 1);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawRegion("-top"),
                new DrawRegion("-antenna", .3f) {{
                    x = 4f;
                    y = -4f;
                }}
            );

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 250, AeyamaItems.iron, 100)); //TODO
        }};
    }
}
