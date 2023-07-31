package aeyama;

import mindustry.mod.*;

import aeyama.content.*;
import aeyama.ui.*;

public class Aeyama extends Mod {

    @Override
    public void init() {
        AeyamaVars.load();
        AeyamaDialogs.load();
    }

    @Override
    public void loadContent() { // The load order is VERY IMPORTANT, don't change it.
        AeyamaAttributes.load();
        AeyamaStatusEffects.load();
        AeyamaLoadouts.load();

        AeyamaUnits.load();
        AeyamaItems.load();
        AeyamaLiquids.load();
        AeyamaBlocks.load();

        AeyamaPlanets.load();
        AeyamaSectors.load();
        AeyamaTechTree.load();
    }
}
