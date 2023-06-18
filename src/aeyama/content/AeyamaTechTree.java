package aeyama.content;

import arc.struct.*;

import mindustry.game.Objectives.*;

import aeyama.content.blocks.*;

import static mindustry.content.TechTree.*;
import static mindustry.type.ItemStack.*;

public class AeyamaTechTree {
    
    public static void load() {
        AeyamaPlanets.aeyama.techTree = nodeRoot("Aeyama", AeyamaItems.planet, () -> {
            node(AeyamaStorageBlocks.coreDropPod, () -> {
                node(AeyamaDistributionBlocks.woodConveyor, with(AeyamaItems.woodLumber, 10, AeyamaItems.stone, 5), Seq.with(new Research(AeyamaProductionBlocks.woodHarvester)), () -> {
                    node(AeyamaDistributionBlocks.ironConveyor, with(/*TODO*/), Seq.with(new Produce(AeyamaItems.iron)), () -> {
                        node(AeyamaDistributionBlocks.steelConveyor, with(/*TODO*/), Seq.with(new Produce(AeyamaItems.iron), new Produce(AeyamaItems.steel)), () -> {

                        });
                        node(AeyamaDistributionBlocks.ironRouter, with(/*TODO*/), Seq.with(new Produce(AeyamaItems.iron)), () -> {
                            node(AeyamaDistributionBlocks.ironJunction, with(/*TODO*/), Seq.with(new Produce(AeyamaItems.iron)), () -> {

                            });
                            node(AeyamaDistributionBlocks.ironBridge, with(/*TODO*/), Seq.with(new Produce(AeyamaItems.iron)), () -> {

                            });
                        });
                    });
                    node(AeyamaDistributionBlocks.woodRouter, with(AeyamaItems.woodLumber, 20, AeyamaItems.stone, 10), Seq.with(new Produce(AeyamaItems.rawIron)), () -> {

                    });
                });
                node(AeyamaStorageBlocks.coreFrontline, with(AeyamaItems.woodLumber, 900, AeyamaItems.stoneBrick, 2400, AeyamaItems.rawIron, 900, AeyamaItems.blueprint, 1), Seq.with(new Produce(AeyamaItems.blueprint)/*, new Produce(AeyamaUnitTypes.sms)*/), () -> {
                    node(AeyamaStorageBlocks.coreControl, with(AeyamaItems.woodLumber, 1000, AeyamaItems.stoneBrick, 1000, AeyamaItems.iron, 500, AeyamaItems.specialBPDefense, 3), () -> {

                    });
                    node(AeyamaStorageBlocks.groundScanner, with(AeyamaItems.iron, 600, AeyamaItems.blueprint, 1), Seq.with(new Produce(AeyamaItems.iron), new Produce(AeyamaItems.copper), new Produce(AeyamaItems.zinc), new SectorComplete(AeyamaSectors.encounter)), () -> {

                    });
                });
                node(AeyamaProductionBlocks.woodHarvester, with(AeyamaItems.woodLumber, 80), () -> {
                    node(AeyamaProductionBlocks.stoneMiner, with(AeyamaItems.woodLumber, 140), () -> {
                        node(AeyamaProductionBlocks.ironMiner, with(AeyamaItems.woodLumber, 80, AeyamaItems.stoneBrick, 200), () -> {
                            
                        });
                        node(AeyamaProductionBlocks.copperMiner, with(AeyamaItems.iron, 80, AeyamaItems.stoneBrick, 200, AeyamaItems.blueprint, 1), () -> {
                            
                        });
                        node(AeyamaProductionBlocks.zincMiner, with(AeyamaItems.iron, 80, AeyamaItems.stoneBrick, 200, AeyamaItems.blueprint, 1), () -> {
                            
                        });
                    });
                });
                node(AeyamaStorageBlocks.smallStockpile, with(AeyamaItems.woodLumber, 120, AeyamaItems.stoneBrick, 300, AeyamaItems.blueprint, 1), Seq.with(new Produce(AeyamaItems.rawIron)), () -> {
                    node(AeyamaStorageBlocks.stockpile, with(AeyamaItems.woodLumber, 1200, AeyamaItems.stoneBrick, 1800, AeyamaItems.iron, 600, AeyamaItems.blueprint, 12), () -> {
                        node(AeyamaStorageBlocks.largeStockpile, with(AeyamaItems.woodLumber, 3750, AeyamaItems.stoneBrick, 4500, AeyamaItems.iron, 1000, AeyamaItems.advancedBlueprint, 18), () -> {
                            
                        });
                    });
                });
                node(AeyamaProductionBlocks.brickMaker, with(AeyamaItems.woodLumber, 300, AeyamaItems.stone, 200), Seq.with(new Produce(AeyamaItems.woodLumber), new Produce(AeyamaItems.stone)), () -> {
                    node(AeyamaProductionBlocks.researchStation, with(AeyamaItems.woodLumber, 400, AeyamaItems.stoneBrick, 600, AeyamaItems.rawIron, 250), Seq.with(new Produce(AeyamaItems.woodLumber), new Produce(AeyamaItems.stoneBrick), new Produce(AeyamaItems.rawIron)), () -> {
                        node(AeyamaProductionBlocks.woodDryer, with(AeyamaItems.woodLumber, 300, AeyamaItems.stoneBrick, 150, AeyamaItems.blueprint, 1), () -> {
                            node(AeyamaProductionBlocks.shredder, with(AeyamaItems.woodLumber, 500, AeyamaItems.stoneBrick, 200, AeyamaItems.rawIron, 140), Seq.with(new Produce(AeyamaItems.woodLumber), new Produce(AeyamaItems.stoneBrick), new Produce(AeyamaItems.rawIron), new SectorComplete(AeyamaSectors.newWorld)), () -> {
    
                            });
                        });
                        node(AeyamaProductionBlocks.burner, with(AeyamaItems.woodLumber, 150, AeyamaItems.stoneBrick, 375, AeyamaItems.rawIron, 75, AeyamaItems.blueprint, 2), () -> {
                            node(AeyamaProductionBlocks.smelterIron, with(AeyamaItems.woodLumber, 500, AeyamaItems.stoneBrick, 800, AeyamaItems.rawIron, 300, AeyamaItems.blueprint, 4), () -> {
                                node(AeyamaProductionBlocks.foundrySteel, with(AeyamaItems.iron, 300, AeyamaItems.woodLumber, 75, AeyamaItems.stoneBrick, 450, AeyamaItems.blueprint, 2), () -> {
    
                                });
                            });
                            node(AeyamaProductionBlocks.smelterCopper, with(AeyamaItems.woodLumber, 500, AeyamaItems.stoneBrick, 800, AeyamaItems.rawCopper, 300, AeyamaItems.blueprint, 4), () -> {
                                
                            });
                            node(AeyamaProductionBlocks.smelterZinc, with(AeyamaItems.woodLumber, 500, AeyamaItems.stoneBrick, 800, AeyamaItems.rawZinc, 300, AeyamaItems.blueprint, 4), () -> {
                                node(AeyamaProductionBlocks.foundryBrass, with(AeyamaItems.iron, 150, AeyamaItems.woodLumber, 75, AeyamaItems.stoneBrick, 450, AeyamaItems.blueprint, 2), () -> {
        
                                });
    
                            });
                        });
                        node(AeyamaProductionBlocks.researchLab, with(AeyamaItems.woodLumber, 800, AeyamaItems.stoneBrick, 1200, AeyamaItems.rawIron, 500, AeyamaItems.blueprint, 10), () -> {

                        });
                    });
                });
                node(AeyamaDefenseBlocks.thrower, with(AeyamaItems.woodLumber, 45, AeyamaItems.stoneBrick, 30, AeyamaItems.blueprint, 1), Seq.with(new Produce(AeyamaItems.blueprint), new SectorComplete(AeyamaSectors.newWorld)), () -> {
                    node(AeyamaDefenseBlocks.bully, with(AeyamaItems.woodLumber, 200, AeyamaItems.stone, 200), Seq.with(new SectorComplete(AeyamaSectors.newWorld)), () -> {
    
                    });
                    node(AeyamaDefenseBlocks.craker, with(/*TODO*/), Seq.with(new Produce(AeyamaItems.steel), new Produce(AeyamaItems.advancedBlueprint)), () -> {
    
                    });
                    node(AeyamaDefenseBlocks.woodWall, with(AeyamaItems.woodLumber, 20), () -> {
                        node(AeyamaDefenseBlocks.stoneBrickWall, with(AeyamaItems.stoneBrick, 20), () -> {
                            node(AeyamaDefenseBlocks.ironWall, with(AeyamaItems.iron, 20), () -> {
                                node(AeyamaDefenseBlocks.steelWall, with(AeyamaItems.steel, 20), () -> {
                                    node(AeyamaDefenseBlocks.largeSteelWall, with(AeyamaItems.steel, 100), () -> {
                                        
                                    });
                                });
                                node(AeyamaDefenseBlocks.largeIronWall, with(AeyamaItems.iron, 100), () -> {
                                    
                                });
                            });
                            node(AeyamaDefenseBlocks.largeStoneBrickWall, with(AeyamaItems.stoneBrick, 100), () -> {
                                
                            });
                        });
                        node(AeyamaDefenseBlocks.largeWoodWall, with(AeyamaItems.woodLumber, 100), () -> {
                            
                        });
                    });
                });
            });

            nodeProduce(AeyamaItems.woodLumber, () -> {
                nodeProduce(AeyamaItems.blueprint, () -> {
                    nodeProduce(AeyamaItems.advancedBlueprint, () -> {
                        nodeProduce(AeyamaItems.specialBPDefense, () -> {
                            
                        });
                        nodeProduce(AeyamaItems.specialBPOffense, () -> {
                            
                        });
                    });
                });
                nodeProduce(AeyamaItems.carbon, () -> {

                });
                nodeProduce(AeyamaItems.sand, () -> {
                    nodeProduce(AeyamaLiquids.water, () -> {

                    });
                });
                nodeProduce(AeyamaItems.stone, () -> {
                    nodeProduce(AeyamaItems.rawIron, () -> {
                        nodeProduce(AeyamaItems.iron, () -> {
                            nodeProduce(AeyamaItems.steel, () -> {
                                nodeProduce(AeyamaItems.armorPlating, () -> {

                                });
                            });
                        });
                        nodeProduce(AeyamaItems.rawCopper, () -> {
                            nodeProduce(AeyamaItems.copper, () -> {
                                nodeProduce(AeyamaItems.brass, Seq.with(new Produce(AeyamaItems.zinc), new Produce(AeyamaItems.copper)),  () -> {
                                    nodeProduce(AeyamaItems.rifle, Seq.with(new Research(AeyamaProductionBlocks.ammunitionPress)), () -> {
                                        nodeProduce(AeyamaItems.combustibleCanister, Seq.with(new Produce(AeyamaItems.carbon)), () -> {
                                            nodeProduce(AeyamaItems.rocket, () -> {
                                                
                                            });
                                        });
                                        nodeProduce(AeyamaItems.highCaliber, () -> {

                                        });
                                        nodeProduce(AeyamaItems.shotgunShell, () -> {
                                            nodeProduce(AeyamaItems.shotgunSlug, () -> {
    
                                            });
                                        });
                                    });
                                });
                            });
                            nodeProduce(AeyamaItems.rawTin, () -> {
                                nodeProduce(AeyamaItems.tin, () -> {
                                    nodeProduce(AeyamaItems.bronze, () -> {
        
                                    });
                                });
                            });
                        });
                        nodeProduce(AeyamaItems.rawTitanium, () -> {
                            nodeProduce(AeyamaItems.titanium, () -> {
                                
                            });
                        });
                        nodeProduce(AeyamaItems.rawZinc, () -> {
                            nodeProduce(AeyamaItems.rawAluminium, () -> {
                                nodeProduce(AeyamaItems.aluminium, () -> {
                                    
                                });
                            });
                            nodeProduce(AeyamaItems.zinc, () -> {
                                
                            });
                        });
                    });
                    nodeProduce(AeyamaItems.stonePebbles, () -> {
                        
                    });
                    nodeProduce(AeyamaItems.stoneBrick, () -> {

                    });
                });
                nodeProduce(AeyamaItems.woodLumberDry, () -> {

                });
                nodeProduce(AeyamaItems.woodShreds, () -> {
                    
                });
            });

            node(AeyamaSectors.newWorld, () -> {
                node(AeyamaSectors.encounter, Seq.with(new SectorComplete(AeyamaSectors.newWorld)), () -> {
    
                });
            });

            node(AeyamaUnitTypes.colonist, () -> {
                node(AeyamaUnitTypes.sms, with(AeyamaItems.armorPlating, 100), () -> {
                    node(AeyamaUnitTypes.assault, with(AeyamaItems.armorPlating, 200), () -> {
    
                    });
                    node(AeyamaUnitTypes.heavy, with(AeyamaItems.armorPlating, 500), () -> {
    
                    });
                });
            });
        });
    }
}
