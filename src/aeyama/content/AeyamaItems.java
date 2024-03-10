package aeyama.content;

import arc.graphics.*;
import arc.struct.*;

import mindustry.type.*;

public class AeyamaItems {
    public static Item 
    /* Ressources */
    woodLumber, woodShreds, woodLumberDry, carbon,
    stone, stonePebbles, stoneBrick, rawIron, iron,
    steel, rawCopper, copper, brass, rawZinc, zinc,
    rawTin, tin, bronze, rawAluminum, aluminum,
    rawTitanium, titanium, blueprint, advancedBlueprint,
    specialBPDefense, specialBPOffense, emptyBattery,
    battery, emptyBarrel,

    /* Ammunition */
    rifle, highCaliber, combustibleCanister, rocket,
    shotgunShell, shotgunSlug,
    
    /* Other */
    armorPlating, planet;
    
    public static final Seq<Item> aeyamaItems = new Seq<>();

    public static void load() {
        /* Ressources */
        woodLumber = new Item("wood-lumber", Color.valueOf("#bf7d5a")) {{            
            flammability = 0.9f;
        }};
        woodShreds = new Item("wood-shreds", Color.valueOf("#efbf8f")) {{
            flammability = 0.8f;
        }};
        woodLumberDry = new Item("wood-lumber-dry", Color.valueOf("#7b6b60")) {{
            flammability = 2f;
        }};
        carbon = new Item("carbon", Color.valueOf("#313131")) {{
            flammability = 0.3f;
        }};
        stone = new Item("stone", Color.valueOf("#e0b28d")) {{            

        }};
        stonePebbles = new Item("stone-pebbles", Color.valueOf("#e0b28c")) {{

        }};
        stoneBrick = new Item("stone-brick", Color.valueOf("#e0b28c")) {{

        }};
        rawIron = new Item("raw-iron", Color.valueOf("#4f4f4f")) {{
            charge = 0.5f;
            hardness = 1;
        }};
        iron = new Item("iron", Color.valueOf("#3e3e3e")) {{
            charge = 0.5f;
        }};
        steel = new Item("steel", Color.valueOf("#cbcbcb")) {{
            charge = 0.5f;
        }};
        rawCopper = new Item("raw-copper", Color.valueOf("#eac2a9")) {{
            hardness = 1;
        }};
        copper = new Item("copper", Color.valueOf("#eac2a9")) {{
            charge = 0.8f;
        }};
        brass = new Item("brass", Color.valueOf("#b3a57d")) {{
            charge = 0.3f;
        }};
        rawZinc = new Item("raw-zinc", Color.valueOf("#9d9d9d")) {{
            charge = 0.7f;
            hardness = 1;
        }};
        zinc = new Item("zinc", Color.valueOf("#d2d2d2")) {{
            charge = 0.7f;
        }};
        rawTin = new Item("raw-tin", Color.valueOf("#000000")) {{
            hardness = 2;
        }};
        tin = new Item("tin", Color.valueOf("#000000")) {{

        }};
        bronze = new Item("bronze", Color.valueOf("#000000")) {{

        }};
        rawAluminum = new Item("raw-aluminum", Color.valueOf("#000000")) {{
            hardness = 2;
        }};
        aluminum = new Item("aluminum", Color.valueOf("#000000")) {{

        }};
        rawTitanium = new Item("raw-titanium", Color.valueOf("#000000")) {{
            charge = 0.5f;
            hardness = 2;
        }};
        titanium = new Item("titanium", Color.valueOf("#000000")) {{

        }};
        blueprint = new Item("blueprint", Color.valueOf("#64bcfc")) {{
            flammability = 1f;
        }};
        advancedBlueprint = new Item("advanced-blueprint", Color.valueOf("#3b3b3b")) {{
            flammability = 1f;
        }};
        specialBPDefense = new Item("special-blueprint-defense", Color.valueOf("#23d200")) {{
            flammability = 1f;
        }};
        specialBPOffense = new Item("special-blueprint-offense", Color.valueOf("#d20000")) {{
            flammability = 1f;
        }};
        emptyBattery = new Item("empty-battery", Color.valueOf("#ffac00")) {{
            explosiveness = 0f;
            flammability = 0.5f;
            radioactivity = 0f;
            charge = 1f;
        }};
        battery = new Item("battery", Color.valueOf("#ffec00")) {{
            explosiveness = 1f;
            flammability = 0.7f;
            radioactivity = 0f;
            charge = 1f;
        }};
        emptyBarrel = new Item("empty-barrel", Color.valueOf("#7f7f7f")) {{
            explosiveness = 0.2f;
            flammability = 0.2f;
            radioactivity = 0f;
            charge = 0f;
        }};

        /* Ammunition */
        rifle = new Item("ammunition-rifle", Color.valueOf("#b3a377")) {{

        }};
        highCaliber = new Item("ammunition-high-caliber", Color.valueOf("#b3a377")) {{

        }};
        combustibleCanister = new Item("ammunition-combustible-canister", Color.valueOf("#b3a377")) {{
            explosiveness = 1f;
            flammability = 2f;

        }};
        rocket = new Item("ammunition-rocket", Color.valueOf("#b3a377")) {{
            explosiveness = 2f;

        }};
        shotgunShell = new Item("ammunition-shotgun-shell", Color.valueOf("#b3a377")) {{

        }};
        shotgunSlug = new Item("ammunition-shotgun-slug", Color.valueOf("#b3a377")) {{

        }};

        /* Other */
        planet = new Item("planet") {{
            alwaysUnlocked = true;
            hidden = true;
        }};
        armorPlating = new Item("armor-plating", Color.valueOf("#000000")) {{

        }};

        aeyamaItems.addAll(
            woodLumber, woodShreds, woodLumberDry, carbon,
            stone, stonePebbles, stoneBrick, rawIron, iron,
            steel, rawCopper, copper, brass, rawZinc, zinc,
            rawTin, tin, bronze, rawAluminum, aluminum,
            rawTitanium, titanium, blueprint, advancedBlueprint,
            specialBPDefense, specialBPOffense, rifle, highCaliber,
            combustibleCanister, rocket, shotgunShell, shotgunSlug,
            planet, armorPlating
        );
    }
}
