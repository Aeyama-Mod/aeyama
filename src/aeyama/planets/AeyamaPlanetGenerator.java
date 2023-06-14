package aeyama.planets;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.noise.*;

import mindustry.*;
import mindustry.ai.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.game.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;

import static aeyama.content.blocks.AeyamaEnvironmentBlocks.*;

public class AeyamaPlanetGenerator extends PlanetGenerator {
    BaseGenerator basegen = new BaseGenerator();
    float scl = 5f;
    float waterOffset = 0.07f;
    
    Block[] terrain = {floorGrassA, floorGrassA, floorGrassB, floorGrassB, floorGrassB, floorGrassC, floorGrassC, floorGrassD, floorGrassD};

    float water = 2f / terrain.length;
    
    float rawHeight(Vec3 position) {
        position = Tmp.v33.set(position).scl(scl);
        return (Mathf.pow(Simplex.noise3d(seed, 7, .5f, 1/3, position.x, position.y, position.z), 2.3f) + waterOffset) / (1 + waterOffset);
    }

    @Override
    public float getHeight(Vec3 position) {
        return Math.max(rawHeight(position), water);
    }

    @Override
    public Color getColor(Vec3 position) {
        Block block = getBlock(position);
        if (block == null) return floorGrassB.mapColor;
        return Tmp.c1.set(block.mapColor).a(1f - block.albedo);
    }

    @Override
    public void genTile(Vec3 position, TileGen tile) {
        tile.floor = getBlock(position);
        this.block = tile.floor.asFloor().wall;

        if (Ridged.noise3d(seed, position.x, position.y, position.z, 22) > .32f) {
            tile.block = Blocks.air;
        }
    }

    Block getBlock(Vec3 position) {
        float height = rawHeight(position);
        Tmp.v31.set(position);

        position = Tmp.v33.set(position).scl(scl);
        float rad = scl;
        float temp = Mathf.clamp(Math.abs(position.y * 2) / rad);
        float tnoise = Simplex.noise3d(seed, 7, .56f, 1/3, position.x, position.y + 999, position.z);

        temp = Mathf.lerp(temp, tnoise, .5f);
        height *= 1.2f;
        height = Mathf.clamp(height);

        Block result = terrain[(int) Mathf.clamp(Math.floor(temp * terrain.length), 0, terrain.length - 1)];
        return result;
    }

    protected float noiseOct(float x, float y, double octaves, double falloff, double scl) {
        Vec3 v = sector.rect.project(x, y).scl(5);
        return Simplex.noise3d(seed, octaves, falloff, 1 / scl, v.x, v.y, v.z);
    }

    @Override
    public void generate(Tiles tiles, Sector sec, int seed) {
        this.tiles = tiles;
        this.sector = sec;
        this.rand.setSeed(sec.id);
        
        TileGen gen = new TileGen();
        for (int y=0; y < tiles.height; y++) {
            for (int x=0; x < tiles.width; x++) {
                gen.reset();
                Vec3 position = sector.rect.project(x / tiles.width, y / tiles.height);

                genTile(position, gen);
                tiles.set(x, y, new Tile(x, y, gen.floor, gen.overlay, gen.block));
            }
        }
        
        class Room {
            int x, y, radius;
            ObjectSet<Room> connected = new ObjectSet<>();

            Room(int x, int y, int radius) {
                this.x = x;
                this.y = y;
                this.radius = radius;
                connected.add(this);
            }

            void connect(Room to) {
                if (connected.contains(to)) return;

                connected.add(to);

                int nscl = rand.random(20, 60);
                int stroke = rand.random(4, 12);

                brush(pathfind(x, y, to.x, to.y, tile -> (tile.solid() ? 5 : 0) + noiseOct(tile.x, tile.y, 1, 1, 1 / nscl * 60) * 60, Astar.manhattan), stroke);
            }
        }

        cells(4);
        distort(10f, 12f);

        width = tiles.width;
        height = tiles.height;

        float constraint = 1.3f;
        float radius = width / 2 / Mathf.sqrt3;
        int rooms = rand.random(2, 5);
        Seq<Room> roomseq = new Seq<>();

        for (int i=0; i < rooms; i++) {
            Tmp.v1.trns(rand.random(360), rand.random(radius / constraint));
            float rx = (float) Math.floor(width / 2 + Tmp.v1.x);
            float ry = (float) Math.floor(height / 2 + Tmp.v1.y);
            float maxrad = radius - Tmp.v1.len();
            float rrad = (float) Math.floor(Math.min(rand.random(9, maxrad / 2), 30));

            roomseq.add(new Room((int) rx, (int) ry, (int) rrad));
        }

        Room spawn = null;
        Seq<Room> enemies = new Seq<>();
        int enemySpawns = rand.random(1, Math.max((int) Math.floor(sector.threat * 4), 1));

        int offset = rand.nextInt(360);
        float length = width / 2.55f - rand.random(12, 23);
        int angleStep = 5;
        int waterCheckRad = 5;
        for (int i=0; i < 360; i += angleStep) {
            int angle = offset + i;
            int cx = (int) Math.floor(width / 2 + Angles.trnsx(angle, length));
            int cy = (int) Math.floor(height / 2 + Angles.trnsy(angle, length));

            int waterTiles = 0;

            for (int rx = -waterCheckRad; rx <= waterCheckRad; rx++) {
                for (int ry = -waterCheckRad; ry <= waterCheckRad; ry++) {
                    Tile tile = tiles.get(cx + rx, cy + ry);

                    if (tile == null || tile.floor().liquidDrop != null) {
                        waterTiles++;
                    }
                }
            }

            if (waterTiles <= 4 || (i + angleStep >= 360)) {
                spawn = new Room(cx, cy, rand.random(10, 18));
                roomseq.add(spawn);

                for (int j=0; j < enemySpawns; j++) {
                    float enemyOffset = rand.range(60);

                    Tmp.v1.set(cx - width / 2, cy - height / 2).rotate(180 + enemyOffset).add(width / 2, this.height / 2);
                    Room espawn = new Room((int) Math.floor(Tmp.v1.x), (int) Math.floor(Tmp.v1.y), rand.random(10, 16));
                    roomseq.add(espawn);
                    enemies.add(espawn);
                };

                break;
            }

        }
        for (Room room : roomseq) {
            erase(room.x, room.y, room.radius);
        }

        int connections = rand.random(Math.max(rooms - 1, 1), rooms + 3);
        for (int i=0; i < connections; i++) {
            roomseq.random(rand).connect(roomseq.random(rand));
        }

        for (Room room : roomseq) {
            spawn.connect(room);
        }

        cells(1);
        distort(10, 6);

        inverseFloodFill(tiles.getn(spawn.x, spawn.y));

        Seq<Block> ores = Seq.with(floorIron);
        float poles = Math.abs(sector.tile.v.y);
        float nmag = .5f;
        float scl = 1;
        float addscl = 1.3f;

        if (Simplex.noise3d(seed, 2, .5f, scl, sector.tile.v.x + 1, sector.tile.v.y, sector.tile.v.z) * nmag + poles > .5f * addscl) {
            ores.add(floorZinc);
        }

        FloatSeq frequencies = new FloatSeq();
        for (int i=0; i < ores.size; i++) {
            frequencies.add(rand.random(-.1f, .01f) - i * .01f + poles * .04f);
        }

        pass((x, y) -> {
            if (!floor.asFloor().hasSurface()) return;

            int offsetX = x - 4, offsetY = y + 23;
            for (int i = ores.size - 1; i >= 0; i--) {
                Block entry = ores.get(i);
                float freq = frequencies.get(i);

                if (Math.abs(.5f - noiseOct(offsetX, offsetY + i * 999, 2, .7f, (40 + i * 2))) > .22f + i * .01f &&
                    Math.abs(.5f - noiseOct(offsetX, offsetY - i * 999, 1, 1, (30 + i * 4))) > .37f + freq) {
                        ore = entry;
                        break;
                }
            }
        });

        trimDark();
        median(2);
        tech();

        float difficulty = sector.threat;
        ints.clear();
        ints.ensureCapacity(width * height / 4);

        Schematics.placeLaunchLoadout(spawn.x, spawn.y);

        for (Room espawn : enemies) {
            tiles.getn(espawn.x, espawn.y).setOverlay(Blocks.spawn);
        }

        GameState state = Vars.state;

        if (sector.hasEnemyBase()) {
            basegen.generate(tiles, enemies.map(r -> tiles.getn(r.x, r.y)), tiles.get(spawn.x, spawn.y), state.rules.waveTeam, sector, difficulty);

            state.rules.attackMode = sector.info.attack = true;
        } else {
            state.rules.winWave = sector.info.winWave = (int) (10 + 5 * Math.max(difficulty * 10, 1));
        }

        float waveTimeDec = .4f;

        state.rules.waveSpacing = Mathf.lerp(60 * 65 * 2, 60 * 60 * 1, (float) Math.floor(Math.max(difficulty - waveTimeDec, 0) / .8f));
        state.rules.waves = sector.info.waves = true;
        state.rules.enemyCoreBuildRadius = 480;

        state.rules.spawns = Waves.generate(difficulty, new Rand(), state.rules.attackMode);
    }

    @Override
    public void postGenerate(Tiles tiles) {
        if (this.sector.hasEnemyBase()) basegen.postGenerate();
    }
}
