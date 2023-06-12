package aeyama.content;

import mindustry.type.*;

public class AeyamaSectors {
    public static SectorPreset
    newWorld, encounter;
    
    public static void load() {
        newWorld = new SectorPreset("new-world", AeyamaPlanets.aeyama, 0) {{
            alwaysUnlocked = true;
            difficulty = 0;
        }};
        encounter = new SectorPreset("encounter", AeyamaPlanets.aeyama, 14) {{
            difficulty = 1;
        }};
    }
}
