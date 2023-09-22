package aeyama.ui.dialogs;

import arc.scene.style.*;

import aeyama.content.*;

import static mindustry.Vars.*;

public class SettingsMenuDialog {

    public static void load() {
        ui.settings.addCategory("@aeyama.displayName-simple", new TextureRegionDrawable(AeyamaItems.planet.uiIcon), t -> {
            t.checkPref("aeyama-showNews", true);
            t.checkPref("aeyama-checkUpdate", true);
            t.checkPref("aeyama-checkBetaUpdate", false);

        });
    }
}
