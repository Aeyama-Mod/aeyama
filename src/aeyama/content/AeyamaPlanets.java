package aeyama.content;

import arc.graphics.*;

import mindustry.content.*;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.*;
import mindustry.type.*;

import aeyama.content.blocks.*;
import aeyama.planets.*;

public class AeyamaPlanets {
    public static Planet
    aeyama, moon;

    public static void load() {
        aeyama = new Planet("aeyama", Planets.sun, 1f, 1) {{
            iconColor = Color.valueOf("#158400");
            generator = new AeyamaPlanetGenerator();

            accessible = true;
            alwaysUnlocked = true;
            defaultCore = AeyamaStorageBlocks.coreDropPod;

            drawOrbit = true;
            orbitRadius = 32f;

            rotateTime = 24 * 60; // 24 minutes for a Aeyama day
            orbitTime = rotateTime * 28; // Full year in 28 Aeyama days

            atmosphereColor = Color.valueOf("#21c7eb");
            atmosphereRadIn = 0.1f;
            atmosphereRadOut = 0.3f;

            sectorSeed = 5948;
            startSector = 0;
            clearSectorOnLose = true;
            allowSectorInvasion = false;
            allowLaunchToNumbered = false;

            ruleSetter = r -> {
                r.fog = true;
                r.staticFog = true;
            };

            itemWhitelist = AeyamaItems.aeyamaItems;

            meshLoader = () -> new HexMesh(this, 6);

            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 5, 1.5f, .12f, 5, Color.valueOf("#7eb6debb"), 1, 2f, 2f, .45f)
            );
        }};

        moon = new Planet("moon", aeyama, 0.5f) {{
            visible = true;
            accessible = false;
            alwaysUnlocked = false;

            drawOrbit = true;
            orbitRadius = 5.2f;
            rotateTime = aeyama.rotateTime * .5f; //Full rotation in half a Aeyama day
            orbitTime = aeyama.rotateTime * 7; //Full orbit per Aeyama week

            hasAtmosphere = false;

            meshLoader = () -> new NoiseMesh(this, 4689, 3,
                                             Color.valueOf("#7d6e64"), .45f, 5, 1.2f, .45f, 1.2f);
        }};
    }
}
