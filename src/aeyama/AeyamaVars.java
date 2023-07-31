package aeyama;

import mindustry.*;
import mindustry.mod.Mods.*;

public class AeyamaVars {
    public static String repo = "Aeyama-Mod/aeyama/";
    public static boolean isDev;
    public static boolean isBeta;

    public static void load() {
        LoadedMod mod = Vars.mods.getMod("aeyama");
        isDev = mod.meta.version.contains("dev");
        isBeta = mod.meta.version.contains("beta");
    }
}
