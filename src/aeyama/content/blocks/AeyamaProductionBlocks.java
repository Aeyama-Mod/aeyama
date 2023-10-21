package aeyama.content.blocks;

import arc.graphics.*;
import arc.struct.*;

import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import aeyama.content.*;
import multicraft.*;

import static mindustry.type.ItemStack.*;

public class AeyamaProductionBlocks {
    public static Block
    /* Drills */
    woodHarvester, stoneMiner, ironMiner, copperMiner, zincMiner,

    /* Factory */
    shredder, woodDryer, burner, brickMaker, ammunitionPress,
    researchStation, researchLab, oreSmelter, oreFoundry;

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
            ambientSound = Sounds.drill;

            requirements(Category.production, with(AeyamaItems.woodLumber, 25, AeyamaItems.stoneBrick, 45));
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
            ambientSound = Sounds.drill;

            requirements(Category.production, with(AeyamaItems.woodLumber, 70, AeyamaItems.stoneBrick, 65, AeyamaItems.iron, 20));
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
            ambientSound = Sounds.drill;

            requirements(Category.production, with(AeyamaItems.woodLumber, 70, AeyamaItems.stoneBrick, 65, AeyamaItems.iron, 20));
        }};
        
        /* Factory */
        shredder = new MultiCrafter("shredder") {{
            scaledHealth = 278;
            size = 2;

            resolvedRecipes = Seq.with(
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.woodLumber, 1)),
                        Seq.with()
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.woodShreds, 5)),
                        Seq.with()
                    ), 5f * 60f
                ),
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.woodLumberDry, 1)),
                        Seq.with()
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.woodShreds, 5)),
                        Seq.with()
                    ), 2.5f * 60f
                ),
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.stone, 1)),
                        Seq.with()
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.stonePebbles, 5)),
                        Seq.with()
                    ), 2.5f * 60f
                ),
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.stoneBrick, 1)),
                        Seq.with()
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.stonePebbles, 10)),
                        Seq.with()
                    ), 5f * 60f
                )
            );

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawRegion("-blade", 3, true),
                    new DrawRegion("-blade", -3, true)
            );

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 125, AeyamaItems.stoneBrick, 50, AeyamaItems.rawIron, 35));
        }};
        woodDryer = new GenericCrafter("wood-dryer") {{
            scaledHealth = 200f;
            size = 2;

            craftTime = 4f * 60f;
            consumeItem(AeyamaItems.woodLumber, 1);
            outputItems = with(AeyamaItems.woodLumberDry, 1);

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 125, AeyamaItems.stoneBrick, 75));
        }};
        burner = new HeatProducer("burner") {{
            health = 360;

            craftTime = 5f * 60f;
            consume(new ConsumeItemFlammable() {{ minFlammability = 0.8f; }});
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
            unitType = AeyamaUnits.sms;

            requirements(Category.effect, BuildVisibility.editorOnly, with());
        }};
        oreSmelter = new MultiCrafter("ore-smelter") {{
            health = 500;
            size = 2;

            menu = "transform";
            maxEfficiency = 1f;
            overheatScale = 1f;

            resolvedRecipes = Seq.with(
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.rawIron, 3)),
                        Seq.with(),
                        0f, 100f
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.iron, 1)),
                        Seq.with()
                    ), 4f * 60f
                ),
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.rawCopper, 3)),
                        Seq.with(),
                        0f, 100f
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.copper, 1)),
                        Seq.with()
                    ), 4f * 60f
                ),
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.rawZinc, 3)),
                        Seq.with(),
                        0f, 100f
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.zinc, 1)),
                        Seq.with()
                    ), 4f * 60f
                )
            );

            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawFlame(Color.valueOf("#FF9940")),
                new DrawRecipe() {{
                    drawers = new DrawBlock[] {
                        new DrawRegion("-iron"),
                        new DrawRegion("-copper"),
                        new DrawRegion("-zinc")
                    };
                }}
            );

            requirements(Category.crafting, with(AeyamaItems.stoneBrick, 125, AeyamaItems.woodLumber, 50, AeyamaItems.rawIron, 25));
        }};
        oreFoundry = new MultiCrafter("ore-foundry") {{
            health = 500;
            size = 3;

            menu = "transform";
            maxEfficiency = 1f;
            overheatScale = 1f;

            resolvedRecipes = Seq.with(
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.copper, 2, AeyamaItems.zinc, 2)),
                        Seq.with(),
                        0f, 200f
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.brass, 1)),
                        Seq.with()
                    ), 8f * 60f
                ),
                new Recipe(
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.iron, 2, AeyamaItems.carbon, 4)),
                        Seq.with(),
                        0f, 100f
                    ),
                    new IOEntry(
                        Seq.with(ItemStack.with(AeyamaItems.steel, 1)),
                        Seq.with()
                    ), 8f * 60f
                )
            );

            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawGlowRegion("-glow")
                // new DrawRecipe() {{
                //     drawers = new DrawBlock[] {
                //         new DrawRegion("-iron"),
                //         new DrawRegion("-copper")
                //     };
                // }}
            );

            requirements(Category.crafting, with(AeyamaItems.stoneBrick, 300, AeyamaItems.woodLumber, 120, AeyamaItems.iron, 50));
        }};
        researchStation = new GenericCrafter("research-station") {{
            health = 800;
            size = 3;

            craftTime = 20f * 60f;
            outputItems = with(AeyamaItems.blueprint, 1);

            requirements(Category.crafting, with(AeyamaItems.woodLumber, 80, AeyamaItems.stoneBrick, 120, AeyamaItems.rawIron, 50));
        }};
        researchLab = new GenericCrafter("research-lab") {{
            scaledHealth = 263f;
            size = 4;

            craftTime = 60f * 60f;
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
