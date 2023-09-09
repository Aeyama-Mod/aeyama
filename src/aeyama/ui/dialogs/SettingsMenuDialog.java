package aeyama.ui.dialogs;

import mindustry.gen.*;

import static mindustry.Vars.*;

public class SettingsMenuDialog {

    public static void load() {
        ui.settings.addCategory("@aeyama.displayName-simple", Icon.planet, t -> {
            t.checkPref("aeyama-showNews", true);
            t.checkPref("aeyama-checkUpdate", true);
            t.checkPref("aeyama-checkBetaUpdate", false);
        });
    }
}
