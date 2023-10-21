package aeyama;

import mindustry.*;
import mindustry.mod.*;
import mindustry.type.*;

import aeyama.content.*;
import aeyama.ui.dialogs.*;

import static arc.Core.*;

public class Aeyama extends Mod {

    @Override
    public void init() {
        AeyamaVars.load();
        SettingsMenuDialog.load();

        if (settings.getBool("aeyama-showNews")) NewsDialog.load();
        if (settings.getBool("aeyama-checkUpdate")) AeyamaUpdater.check();

        // Hide the items of this mod on all the other planets.
        for (Planet planet : Vars.content.planets()) {
            if (planet.name != "aeyama")
                planet.hiddenItems.addAll(AeyamaItems.aeyamaItems);
        }
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
