package aeyama.content;

import arc.graphics.*;
import mindustry.type.*;

public class AeyamaStatusEffects {
    public static StatusEffect
    knockdown, corrosion;

    public static void load() {
        knockdown = new StatusEffect("knockdown") {{
            color = Color.valueOf("#98db17");
            healthMultiplier = 0.8f;
            speedMultiplier = 0.8f;
        }};
        corrosion = new StatusEffect("corrosion") {{
            color = Color.valueOf("#a7dd01");
            speedMultiplier = 0.4f;
            damage = 0.35f;
        }};
    }
}
