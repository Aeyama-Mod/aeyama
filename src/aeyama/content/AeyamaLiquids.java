package aeyama.content;

import arc.graphics.*;

import mindustry.content.*;
import mindustry.type.*;

public class AeyamaLiquids {
    public static Liquid
    water, sulfuricAcid;
    
    public static void load() {
        water = new Liquid("water", Color.valueOf("#596ab8")) {{
            coolant = true;
            effect = StatusEffects.wet;

            heatCapacity = 0.4f;
            boilPoint = 0.5f;
        }};
        sulfuricAcid = new Liquid("sulfuric-acid", Color.valueOf("#a7dd01")) {{
            coolant = false;
            effect = AeyamaStatusEffects.corrosion;

            heatCapacity = 0.45f;
            viscosity = 0.55f;
            temperature = 0.85f;
        }};
    }
}
