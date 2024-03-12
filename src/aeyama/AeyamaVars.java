package aeyama;

import mindustry.*;
import mindustry.mod.Mods.*;

public class AeyamaVars {
    public static LoadedMod mod = Vars.mods.getMod(Aeyama.class);
    public static String repo = "Aeyama-Mod/aeyama";
    public static boolean isDev;
    public static boolean isBeta;

    public static void load() {
        isDev = mod.meta.version.contains("dev");
        isBeta = mod.meta.version.contains("beta") || mod.meta.version.contains("pre");
    }
}
