package aeyama;

import arc.util.*;
import mindustry.mod.*;

public class Aeyama extends Mod {

    @Override
    public void init() {
        AeyamaVars.load();
        
        Log.info("Aeyama Reborn!");
    }

    @Override
    public void loadContent() { // The load order is VERY IMPORTANT, don't change it.
        
    }
}
