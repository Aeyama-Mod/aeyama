package aeyama.content;

import mindustry.type.*;

import static mindustry.type.ItemStack.*;

public class AeyamaSectors {
    public static SectorPreset
    newWorld, encounter;
    
    public static void load() {
        newWorld = new SectorPreset("new-world", AeyamaPlanets.aeyama, 0) {{
            difficulty = 0;
            alwaysUnlocked = true;

            rules = r -> {
                r.loadout.clear();
                r.loadout.addAll(with(AeyamaItems.woodLumber, 75, AeyamaItems.stone, 50));
                r.canGameOver = false;
            };
        }};
        encounter = new SectorPreset("encounter", AeyamaPlanets.aeyama, 14) {{
            difficulty = 1;
            allowLaunchSchematics = true;
            allowLaunchLoadout = true;
            
            rules = r -> {
                r.loadout.clear();
                r.loadout.addAll(with(AeyamaItems.woodLumber, 100, AeyamaItems.stoneBrick, 50, AeyamaItems.rawIron, 10));
            };
        }};
    }
}
