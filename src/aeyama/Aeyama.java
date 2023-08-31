package aeyama;

import mindustry.mod.*;

import aeyama.content.*;
import aeyama.ui.*;
import aeyama.ui.dialogs.*;

import static arc.Core.*;

public class Aeyama extends Mod {

    @Override
    public void init() {
        AeyamaVars.load();
        SettingsMenuDialog.load();
        AeyamaDialogs.load();

        if(settings.getBool("aeyama-checkUpdate")) AeyamaUpdater.check();
    }

    @Override
    public void loadContent() { // The load order is VERY IMPORTANT, don't change it.
        AeyamaAttributes.load();
        AeyamaStatusEffects.load();
        AeyamaLoadouts.load();

        AeyamaWeapons.load();
        AeyamaUnits.load();
        AeyamaItems.load();
        AeyamaLiquids.load();
        AeyamaBlocks.load();

        AeyamaPlanets.load();
        AeyamaSectors.load();
        AeyamaTechTree.load();
    }
}
