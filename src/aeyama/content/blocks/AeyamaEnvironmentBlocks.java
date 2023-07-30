package aeyama.content.blocks;

import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.*;

import aeyama.content.*;

public class AeyamaEnvironmentBlocks {
    public static Block
    /* Floors */
    propsGrass, floorDirtA, floorDirtB, floorDirtC, floorGrassA,
    floorGrassB, floorGrassC, floorGrassD, floorCopper, floorIron,
    floorZinc, floorSand, floorStoneA, floorStoneB, floorStoneC,
    floorDeepWater, floorSandWater, floorSulfuricAcid,
    
    /* Walls */
    wallDirtA, wallDirtB, wallDirtC, wallStoneA, wallStoneB,
    wallStoneC, wallTree;

    public static void load() {
        /* Floors */
        propsGrass = new OverlayFloor("props-grass") {{
            hasShadow = false;
            variants = 9;
        }};
        floorSand = new Floor("floor-sand", 1) {{
            floorSand.asFloor().wall = Blocks.sandWall;
            itemDrop = AeyamaItems.sand;
            attributes.set(Attribute.get("sand"), .25f);
            attributes.set(Attribute.get("stone"), .0875f);
        }};
        floorGrassA = new Floor("floor-grassa", 3) {{
            floorGrassA.asFloor().wall = wallTree;
            attributes.set(Attribute.get("iron"), .025f);
            attributes.set(Attribute.get("zinc"), .025f);
            attributes.set(Attribute.get("copper"), .025f);
            attributes.set(Attribute.get("stone"), .025f);
        }};
        floorGrassB = new Floor("floor-grassb", 3) {{
            floorGrassB.asFloor().wall = wallTree;
            attributes.set(Attribute.get("iron"), .025f);
            attributes.set(Attribute.get("zinc"), .025f);
            attributes.set(Attribute.get("copper"), .025f);
            attributes.set(Attribute.get("stone"), .025f);
        }};
        floorGrassC = new Floor("floor-grassc", 3) {{
            floorGrassC.asFloor().wall = wallTree;
            attributes.set(Attribute.get("iron"), .025f);
            attributes.set(Attribute.get("zinc"), .025f);
            attributes.set(Attribute.get("copper"), .025f);
            attributes.set(Attribute.get("stone"), .025f);
        }};
        floorGrassD = new Floor("floor-grassd", 3) {{
            floorGrassD.asFloor().wall = wallTree;
            attributes.set(Attribute.get("iron"), .025f);
            attributes.set(Attribute.get("zinc"), .025f);
            attributes.set(Attribute.get("copper"), .025f);
            attributes.set(Attribute.get("stone"), .025f);
        }};
        floorDirtA = new Floor("floor-dirta", 3) {{
            floorDirtA.asFloor().wall = wallDirtA;
            attributes.set(Attribute.get("iron"), .05f);
            attributes.set(Attribute.get("zinc"), .05f);
            attributes.set(Attribute.get("copper"), .05f);
            attributes.set(Attribute.get("stone"), .05f);
        }};
        floorDirtB = new Floor("floor-dirtb", 3) {{
            floorDirtB.asFloor().wall = wallDirtB;
            attributes.set(Attribute.get("iron"), .05f);
            attributes.set(Attribute.get("zinc"), .05f);
            attributes.set(Attribute.get("copper"), .05f);
            attributes.set(Attribute.get("stone"), .05f);
        }};
        floorDirtC = new Floor("floor-dirtc", 3) {{
            floorDirtC.asFloor().wall = wallDirtC;
            attributes.set(Attribute.get("iron"), .05f);
            attributes.set(Attribute.get("zinc"), .05f);
            attributes.set(Attribute.get("copper"), .05f);
            attributes.set(Attribute.get("stone"), .05f);
        }};
        floorStoneA = new Floor("floor-stonea", 3) {{
            floorStoneA.asFloor().wall = wallStoneA;
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("iron"), .125f);
            attributes.set(Attribute.get("zinc"), .125f);
            attributes.set(Attribute.get("copper"), .125f);
            attributes.set(Attribute.get("stone"), .25f);
        }};
        floorStoneB = new Floor("floor-stoneb", 3) {{
            floorStoneB.asFloor().wall = wallStoneB;
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("iron"), .125f);
            attributes.set(Attribute.get("zinc"), .125f);
            attributes.set(Attribute.get("copper"), .125f);
            attributes.set(Attribute.get("stone"), .25f);
        }};
        floorStoneC = new Floor("floor-stonec", 3) {{
            floorStoneC.asFloor().wall = wallStoneC;
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("iron"), .125f);
            attributes.set(Attribute.get("zinc"), .125f);
            attributes.set(Attribute.get("copper"), .125f);
            attributes.set(Attribute.get("stone"), .25f);
        }};
        floorIron = new Floor("floor-ore-iron", 3) {{
            itemDrop = AeyamaItems.rawIron;
            attributes.set(Attribute.get("iron"), .25f);
            attributes.set(Attribute.get("zinc"), .075f);
            attributes.set(Attribute.get("copper"), .075f);
            attributes.set(Attribute.get("stone"), .125f);
        }};
        floorCopper = new Floor("floor-ore-copper", 3) {{
            itemDrop = AeyamaItems.rawCopper;
            attributes.set(Attribute.get("iron"), .075f);
            attributes.set(Attribute.get("zinc"), .075f);
            attributes.set(Attribute.get("copper"), .25f);
            attributes.set(Attribute.get("stone"), .125f);
        }};
        floorZinc = new Floor("floor-ore-zinc", 3) {{
            itemDrop = AeyamaItems.rawZinc;
            attributes.set(Attribute.get("iron"), .075f);
            attributes.set(Attribute.get("zinc"), .25f);
            attributes.set(Attribute.get("copper"), .075f);
            attributes.set(Attribute.get("stone"), .125f);
        }};
        
        floorSandWater = new Floor("floor-sand-water", 0) {{
            floorSandWater.asFloor().wall = Blocks.sandWall;
            isLiquid = true;
            liquidDrop = AeyamaLiquids.water;
            attributes.set(Attribute.get("water"), 1f);

            supportsOverlay = true;
            cacheLayer = CacheLayer.water;

            albedo = 0.6f;
            speedMultiplier = 0.8f;
            status = StatusEffects.wet;
            statusDuration = 30f;
        }};
        floorDeepWater = new Floor("floor-deep-water", 0) {{
            floorDeepWater.asFloor().wall = Blocks.sandWall;
            isLiquid = true;
            liquidDrop = AeyamaLiquids.water;
            attributes.set(Attribute.get("water"), 1f);

            supportsOverlay = true;
            cacheLayer = CacheLayer.water;

            albedo = 0.9f;
            speedMultiplier = 0.1f;
            drownTime = 200f;
            status = StatusEffects.wet;
            statusDuration = 90f;
        }};
        floorSulfuricAcid = new Floor("floor-sulfuric-acid", 0) {{
            isLiquid = true;
            liquidDrop = AeyamaLiquids.sulfuricAcid;

            supportsOverlay = true;
            cacheLayer = CacheLayer.water;

            albedo = 0.3f;
            speedMultiplier = 0.7f;
            status = AeyamaStatusEffects.corrosion;
            statusDuration = 7.5f * 60f;
        }};

        /* Walls */
        wallDirtA = new StaticWall("wall-dirta") {{
            variants = 2;
            attributes.set(Attribute.get("stone"), .1f);
        }};
        wallDirtB = new StaticWall("wall-dirtb") {{
            variants = 2;
            attributes.set(Attribute.get("stone"), .1f);
        }};
        wallDirtC = new StaticWall("wall-dirtc") {{
            variants = 2;
            attributes.set(Attribute.get("stone"), .1f);
        }};
        wallStoneA = new StaticWall("wall-stonea") {{
            variants = 2;
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("stone"), .25f);
        }};
        wallStoneB = new StaticWall("wall-stoneb") {{
            variants = 2;
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("stone"), .25f);
        }};
        wallStoneC = new StaticWall("wall-stonec") {{
            variants = 2;
            itemDrop = AeyamaItems.stone;
            attributes.set(Attribute.get("stone"), .25f);
        }};
        wallTree = new StaticWall("wall-tree") {{
            variants = 16;
            itemDrop = AeyamaItems.woodLumber;
            attributes.set(Attribute.get("wood"), 1f);
        }};
    }
}
