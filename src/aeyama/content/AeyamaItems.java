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
    specialBPDefense, specialBPOffense,

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
            buildable = false;
        }};
        woodShreds = new Item("wood-shreds", Color.valueOf("#efbf8f")) {{
            flammability = 0.8f;
            buildable = false;
        }};
        woodLumberDry = new Item("wood-lumber-dry", Color.valueOf("#7b6b60")) {{
            flammability = 2f;
            buildable = false;
        }};
        carbon = new Item("carbon", Color.valueOf("#313131")) {{
            flammability = 0.3f;
            buildable = false;
        }};
        stone = new Item("stone", Color.valueOf("#e0b28d")) {{            
            buildable = false;
        }};
        stonePebbles = new Item("stone-pebbles", Color.valueOf("#e0b28c")) {{
            buildable = false;
        }};
        stoneBrick = new Item("stone-brick", Color.valueOf("#e0b28c")) {{
            buildable = false;
        }};
        rawIron = new Item("raw-iron", Color.valueOf("#4f4f4f")) {{
            charge = 0.5f;
            hardness = 1;
            buildable = false;
        }};
        iron = new Item("iron", Color.valueOf("#3e3e3e")) {{
            charge = 0.5f;
            buildable = false;
        }};
        steel = new Item("steel", Color.valueOf("#cbcbcb")) {{
            charge = 0.5f;
            buildable = false;
        }};
        rawCopper = new Item("raw-copper", Color.valueOf("#eac2a9")) {{
            hardness = 1;
            buildable = false;
        }};
        copper = new Item("copper", Color.valueOf("#eac2a9")) {{
            charge = 0.8f;
            buildable = false;
        }};
        brass = new Item("brass", Color.valueOf("#b3a57d")) {{
            charge = 0.3f;
            buildable = false;
        }};
        rawZinc = new Item("raw-zinc", Color.valueOf("#9d9d9d")) {{
            charge = 0.7f;
            hardness = 1;
            buildable = false;
        }};
        zinc = new Item("zinc", Color.valueOf("#d2d2d2")) {{
            charge = 0.7f;
            buildable = false;
        }};
        rawTin = new Item("raw-tin", Color.valueOf("#000000")) {{
            hardness = 2;
            buildable = false;
        }};
        tin = new Item("tin", Color.valueOf("#000000")) {{
            buildable = false;
        }};
        bronze = new Item("bronze", Color.valueOf("#000000")) {{
            buildable = false;
        }};
        rawAluminum = new Item("raw-aluminum", Color.valueOf("#000000")) {{
            hardness = 2;
            buildable = false;
        }};
        aluminum = new Item("aluminum", Color.valueOf("#000000")) {{
            buildable = false;
        }};
        rawTitanium = new Item("raw-titanium", Color.valueOf("#000000")) {{
            charge = 0.5f;
            hardness = 2;
            buildable = false;
        }};
        titanium = new Item("titanium", Color.valueOf("#000000")) {{
            buildable = false;
        }};
        blueprint = new Item("blueprint", Color.valueOf("#64bcfc")) {{
            flammability = 1f;
            buildable = false;
        }};
        advancedBlueprint = new Item("advanced-blueprint", Color.valueOf("#3b3b3b")) {{
            flammability = 1f;
            buildable = false;
        }};
        specialBPDefense = new Item("special-blueprint-defense", Color.valueOf("#23d200")) {{
            flammability = 1f;
            buildable = false;
        }};
        specialBPOffense = new Item("special-blueprint-offense", Color.valueOf("#d20000")) {{
            flammability = 1f;
            buildable = false;
        }};

        /* Ammunition */
        rifle = new Item("ammunition-rifle", Color.valueOf("#b3a377")) {{
            buildable = false;
        }};
        highCaliber = new Item("ammunition-high-caliber", Color.valueOf("#b3a377")) {{
            buildable = false;
        }};
        combustibleCanister = new Item("ammunition-combustible-canister", Color.valueOf("#b3a377")) {{
            explosiveness = 1f;
            flammability = 2f;
            buildable = false;
        }};
        rocket = new Item("ammunition-rocket", Color.valueOf("#b3a377")) {{
            explosiveness = 2f;
            buildable = false;
        }};
        shotgunShell = new Item("ammunition-shotgun-shell", Color.valueOf("#b3a377")) {{
            buildable = false;
        }};
        shotgunSlug = new Item("ammunition-shotgun-slug", Color.valueOf("#b3a377")) {{
            buildable = false;
        }};

        /* Other */
        planet = new Item("planet") {{
            alwaysUnlocked = true;
            hidden = true;
        }};
        armorPlating = new Item("armor-plating", Color.valueOf("#000000")) {{
            buildable = false;
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
