package aeyama.content;

import arc.graphics.*;

import mindustry.type.*;

public class AeyamaLiquids {
    public static Liquid sulfuricAcid;
    
    public static void load() {
        sulfuricAcid = new Liquid("sulfuric-acid", Color.valueOf("#a7dd01")) {{
            coolant = false;
            effect = AeyamaStatusEffects.corrosion;

            heatCapacity = 0.45f;
            viscosity = 0.55f;
            temperature = 0.85f;
        }};
    }
}
