package aeyama.content;

import arc.graphics.*;
import mindustry.type.*;

public class AeyamaStatuses {
        public static StatusEffect knockdown;
        public static void load(){
            knockdown = new StatusEffect("knockdown"){{
                color = Color.valueOf("#98db17");
                healthMultiplier = 0.8f;
                speedMultiplier = 0.8f;
            }};
        }
}
