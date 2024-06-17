package aeyama.content;

import arc.graphics.*;
import arc.struct.*;

import mindustry.type.*;

public class AeyamaItems {
    public static Item wood, stone;

    public static Seq<Item> aeyamaItems = new Seq<Item>();

    public static void load() {
        //#region Resources
        wood = new Item("wood", Color.valueOf("#bf7d5a")) {{
            
        }};
        stone = new Item("stone", Color.valueOf("#e0b28d")) {{

        }};
        //#endregion

        aeyamaItems.addAll(wood, stone);
    }
}
