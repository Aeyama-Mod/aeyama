package aeyama;

import mindustry.mod.*;

import aeyama.content.*;
import aeyama.ui.*;

public class Aeyama extends Mod {

    @Override
    public void init() {
        AeyamaDialogs.load();
    }

    @Override
    public void loadContent() { // The load order is VERY IMPORTANT
        AeyamaAttributes.load();
        AeyamaUnitTypes.load();
        AeyamaItems.load();
        AeyamaLiquids.load();
        AeyamaBlocks.load();
        AeyamaPlanets.load();
        AeyamaSectors.load();
        AeyamaTechTree.load();
    }
}
