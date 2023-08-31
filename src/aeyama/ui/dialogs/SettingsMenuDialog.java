package aeyama.ui.dialogs;

import arc.util.*;

import mindustry.gen.*;

import static mindustry.Vars.*;

public class SettingsMenuDialog {
    
    public static void load() {
        ui.settings.addCategory("@aeyama.displayName-simple", Icon.planet, t -> {
            t.checkPref("aeyama-checkUpdate", true, a -> {
                Log.info(a);
            });
            t.checkPref("aeyama-checkBetaUpdate", false);
        });
    }
}
