package aeyama.content.blocks;

import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;

import aeyama.content.*;

import static mindustry.type.ItemStack.*;

public class AeyamaDefenseBlocks {
    public static Block
    /* Turrets */
    bully, thrower, craker, penetration,

    /* Walls */
    woodWall, largeWoodWall, stoneBrickWall, largeStoneBrickWall,
    ironWall, largeIronWall, steelWall, largeSteelWall;

    public static void load() {
        /* Turrets */
        bully = new ItemTurret("bully") {{
            health = 240;

            range = 70f;
            reload = 15f;
            maxAmmo = 40;
            inaccuracy = 4f;
            targetGround = true;
            targetAir = true;

            ammo(AeyamaItems.stonePebbles, new BulletType() {{
                reloadMultiplier = 1f;
                ammoMultiplier = 2f;
                lifetime = 60f;
                damage = 7f;
                speed = 1.8f;
                inaccuracy = 12f;

                instantDisappear = true;
                fragRandomSpread = 15f;
                fragBullets = 4;
                fragBullet = new BasicBulletType() {{
                    damage = 4f;
                    speed = 1.8f;
                    lifetime = 40f;
                    width = 4f;
                    height = 4f;
                }};
            }}, AeyamaItems.woodShreds, new BulletType() {{
                reloadMultiplier = 1f;
                ammoMultiplier = 4f;
                lifetime = 60f;
                damage = 3f;
                speed = 1.8f;
                inaccuracy = 12f;

                instantDisappear = true;
                fragRandomSpread = 30f;
                fragBullets = 8;
                fragBullet = new BasicBulletType() {{
                    damage = 2f;
                    speed = 1.8f;
                    lifetime = 30f;
                    width = 2f;
                    height = 2f;
                }};
            }});

            requirements(Category.turret, with(AeyamaItems.woodLumber, 25, AeyamaItems.stoneBrick, 45, AeyamaItems.iron, 5));
        }};
        thrower = new ItemTurret("thrower") {{
            health = 270;

            range = 100f;
            reload = 40f;
            maxAmmo = 40;
            inaccuracy = 4f;
            targetGround = true;
            targetAir = true;
            ammo(AeyamaItems.stone, new BasicBulletType() {{
                reloadMultiplier = 1f;
                ammoMultiplier = 2f;
                lifetime = 60f;
                damage = 24f;
                height = 9.5f;
                speed = 1.8f;
                width = 7f;
            }}, AeyamaItems.woodLumber, new BasicBulletType() {{
                reloadMultiplier = 1f;
                ammoMultiplier = 4f;
                lifetime = 60f;
                damage = 16f;
                height = 7.5f;
                speed = 1.8f;
                width = 5.5f;
            }});

            requirements(Category.turret, with(AeyamaItems.woodLumber, 45, AeyamaItems.stoneBrick, 70, AeyamaItems.iron, 15));
        }};
        craker = new ItemTurret("craker") {{
            health = 310;
            size = 2;

            range = 90f;
            reload = 40f;
            inaccuracy = 4f;
            targetGround = true;
            targetAir = false;
            shoot = new ShootSpread() {{
                shots = 10;
                spread = 10f;
            }};
            ammo(AeyamaItems.woodLumber, new BasicBulletType() {{
                reloadMultiplier = 1f;
                ammoMultiplier = 1f;
                lifetime = 60f;
                damage = 11f;
                height = 8f;
                speed = 1.2f;
                width = 4.5f;
            }}, AeyamaItems.woodLumberDry, new BasicBulletType() {{
                reloadMultiplier = 1f;
                ammoMultiplier = 1.5f;
                lifetime = 90f;
                damage = 16f;
                height = 9f;
                speed = 1.6f;
                width = 6.5f;
            }});

            requirements(Category.turret, with(AeyamaItems.iron, 30, AeyamaItems.woodLumber, 65));
        }};
        penetration = new ItemTurret("penetration") {{
            scaledHealth = 100f;
            size = 2;
            range = 150f;
            reload = 70f;
            maxAmmo = 50;
            targetGround = true;
            targetAir = true;
            ammoPerShot = 5;
            shootY = 0;
            ammo(AeyamaItems.iron, new BasicBulletType(6, 40) {{
                reloadMultiplier = 1f;
                ammoMultiplier = 2f;
                lifetime = 25f;
                height = 10f;
                width = 6f;
                pierce = true;
                pierceCap = 3;
            }}, AeyamaItems.copper, new BasicBulletType(6, 25) {{
                reloadMultiplier = 1f;
                ammoMultiplier = 3f;
                lifetime = 25f;
                height = 10f;
                width = 6f;
                pierce = true;
                pierceCap = 5;
            }});
            researchCostMultiplier = 0.4f;
            requirements(Category.turret, with(AeyamaItems.woodLumber, 90, AeyamaItems.iron, 45, AeyamaItems.zinc, 30)); //TODO
        }};

        /* Walls */
        woodWall = new Wall("wall-wood") {{
            health = 120;

            requirements(Category.defense, with(AeyamaItems.woodLumber, 6));
        }};
        largeWoodWall = new Wall("wall-wood-large") {{
            health = woodWall.health * 4;
            size = 2;

            requirements(Category.defense, with(AeyamaItems.woodLumber, 24));
        }};
        stoneBrickWall = new Wall("wall-stone-brick") {{
            health = 240;

            requirements(Category.defense, with(AeyamaItems.stoneBrick, 6));
        }};
        largeStoneBrickWall = new Wall("wall-stone-brick-large") {{
            health = stoneBrickWall.health * 4;
            size = 2;

            requirements(Category.defense, with(AeyamaItems.stoneBrick, 24));
        }};
        ironWall = new Wall("wall-iron") {{
            scaledHealth = 270f;

            requirements(Category.defense, with(AeyamaItems.iron, 6));
        }};
        largeIronWall = new Wall("wall-iron-large") {{
            scaledHealth = ironWall.health * 4;
            size = 2;

            requirements(Category.defense, with(AeyamaItems.iron, 24));
        }};
        steelWall = new Wall("wall-steel") {{
            scaledHealth = 324f;

            requirements(Category.defense, with(AeyamaItems.steel, 6));
        }};
        largeSteelWall = new Wall("wall-steel-large") {{
            scaledHealth = steelWall.health * 4;
            size = 2;

            requirements(Category.defense, with(AeyamaItems.steel, 24));
        }};
    }
}
