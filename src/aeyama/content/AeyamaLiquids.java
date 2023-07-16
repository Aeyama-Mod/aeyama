package aeyama.content;

import arc.graphics.*;

import mindustry.content.*;
import mindustry.type.*;

public class AeyamaLiquids {
    public static Liquid water;
    
    public static void load() {
        water = new Liquid("water", Color.valueOf("#596ab8")) {{
            coolant = true;
            effect = StatusEffects.wet;

            heatCapacity = 0.4f;
            boilPoint = 0.5f;
        }};
    }
}
