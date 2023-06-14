package aeyama.content;

import arc.graphics.*;

import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;

public class AeyamaUnitTypes {
    public static UnitType
        colonist,
        sms, assault, heavy, scout,
        
        insectCrawler, insectSwarmer, insectSpit;
    
    public static void load() {
        colonist = new UnitType("colonist") {{
            aiController = BuilderAI::new;
            constructor = MechUnit::create;
            isEnemy = false;

            mechSideSway = 0f;
            mechStepParticles = true;
            legSpeed = .5f;

            health = 100f;
            armor = 2f;
            speed = 1.5f;
            drag = 1.25f;
            flying = false;
            omniMovement = true;
            hitSize = 2f;
            itemCapacity = 50;

            buildSpeed = .25f;
            buildRange = 150f;

            canBoost = true;
            boostMultiplier = .5f;

            canAttack = true;
            mineSpeed = 2f;
            mineTier = 0;
            mineFloor = true;
            mineWalls = true;

            coreUnitDock = true;
            createScorch = false;
        }};
        sms = new UnitType("sms") {{
            aiController = BuilderAI::new;
            constructor = MechUnit::create;
            isEnemy = false;

            mechSideSway = 0f;
            mechStepParticles = true;
            legSpeed = .5f;

            health = 300f;
            speed = 1.5f;
            drag = 1.25f;
            flying = false;
            omniMovement = true;
            hitSize = 2f;
            itemCapacity = 10;

            buildSpeed = .25f;
            buildRange = 100f;

            canBoost = true;
            boostMultiplier = .3f;

            canAttack = true;
            mineSpeed = 2f;
            mineTier = 0;
            mineFloor = true;
            mineWalls = true;

            coreUnitDock = true;
            createScorch = false;

            weapons.add(new Weapon("aeyama-taser") {{
                top = false;
                x = 5f;
                y = 3f;
                shootY = 2f;
                reload = 36f;
                recoil = 2f;
                shootSound = Sounds.spark;
                shake = 0.1f;
                alternate = false;
                mirror = false;
                shoot.shots = 3;
                shoot.shotDelay = 0.5f;
                bullet = new LightningBulletType(){{
                    lightningLength = 4;
                    damage = 6f;
                    speed = 0f;
                    collidesTeam = true;
                    healPercent = 2f;
                    lifetime = Fx.lightning.lifetime;
                    hitEffect = Fx.hitLancer;
                    despawnEffect = Fx.none;
                    status = StatusEffects.shocked;
                    statusDuration = 10f;
                    hittable = false;
                    lightColor = Color.white;
                    buildingDamageMultiplier = 0.25f;
                    lightningColor = hitColor =Color.valueOf("#5b6ee1");
                }};
            }});
        }};
        assault = new UnitType("assault") {{
            aiController = BuilderAI::new;
            constructor = MechUnit::create;
            isEnemy = false;

            mechSideSway = 0f;
            mechStepParticles = true;
            legSpeed = .5f;

            health = 300f;
            speed = 1.5f;
            drag = 1.25f;
            flying = false;
            omniMovement = true;
            hitSize = 2f;
            itemCapacity = 10;

            buildSpeed = .25f;
            buildRange = 100f;

            canBoost = true;
            boostMultiplier = .3f;

            canAttack = true;
            mineSpeed = 2f;
            mineTier = 0;
            mineFloor = true;
            mineWalls = true;

            coreUnitDock = true;
            createScorch = false;

            weapons.add(new Weapon("riffle") {{
                aiControllable = false;
                top = false;
                x = 5f;
                y = 3f;
                shootY = 2f;
                reload = 25f;
                recoil = 2f;
                shootSound = Sounds.pew;
                shake = .25f;
                alternate = false;
                mirror = false;
                bullet = new BasicBulletType() {{
                    damage = 14f;
                    speed = 6f;
                    lifetime = 20f;
                    collidesTeam = true;
                    healPercent = 5f;
                    frontColor = Color.valueOf("#ffffff");
                    backColor = Color.valueOf("#98ffa9");
                }};
            }});
        }};
        heavy = new UnitType("heavy") {{
            aiController = BuilderAI::new;
            constructor = MechUnit::create;
            isEnemy = false;

            mechSideSway = 0f;
            mechStepParticles = true;
            legSpeed = .5f;

            health = 300f;
            speed = 1.5f;
            drag = 1.25f;
            flying = false;
            omniMovement = true;
            hitSize = 2f;
            itemCapacity = 10;

            buildSpeed = .25f;
            buildRange = 100f;

            canBoost = true;
            boostMultiplier = .3f;

            canAttack = true;
            mineSpeed = 2f;
            mineTier = 0;
            mineFloor = true;
            mineWalls = true;

            coreUnitDock = true;
            createScorch = false;

            weapons.add(new Weapon("riffle") {{
                aiControllable = false;
                top = false;
                x = 5f;
                y = 3f;
                shootY = 2f;
                reload = 25f;
                recoil = 2f;
                shootSound = Sounds.pew;
                shake = .25f;
                alternate = false;
                mirror = false;
                bullet = new BasicBulletType() {{
                    damage = 14f;
                    speed = 6f;
                    lifetime = 20f;
                    collidesTeam = true;
                    healPercent = 5f;
                    frontColor = Color.valueOf("#ffffff");
                    backColor = Color.valueOf("#98ffa9");
                }};
            }});
        }};
        scout = new UnitType("scout") {{
            aiController = BuilderAI::new;
            constructor = MechUnit::create;
            isEnemy = false;

            mechSideSway = 0f;
            mechStepParticles = true;
            legSpeed = .5f;

            health = 300f;
            speed = 1.5f;
            drag = 1.25f;
            flying = false;
            omniMovement = true;
            hitSize = 2f;
            itemCapacity = 10;

            buildSpeed = .25f;
            buildRange = 100f;

            canBoost = true;
            boostMultiplier = .3f;

            canAttack = true;
            mineSpeed = 2f;
            mineTier = 0;
            mineFloor = true;
            mineWalls = true;

            coreUnitDock = true;
            createScorch = false;

            weapons.add(new Weapon("riffle") {{
                aiControllable = false;
                top = false;
                x = 5f;
                y = 3f;
                shootY = 2f;
                reload = 25f;
                recoil = 2f;
                shootSound = Sounds.pew;
                shake = .25f;
                alternate = false;
                mirror = false;
                bullet = new BasicBulletType() {{
                    damage = 14f;
                    speed = 6f;
                    lifetime = 20f;
                    collidesTeam = true;
                    healPercent = 5f;
                    frontColor = Color.valueOf("#ffffff");
                    backColor = Color.valueOf("#98ffa9");
                }};
            }});
        }};
        insectCrawler = new UnitType("insect-crawler") {{
            aiController = GroundAI::new;
            constructor = LegsUnit::create;
            isEnemy = true;
            groundLayer = 60f;

            legSpeed = .5f;
            legCount = 6;
            legLengthScl = 2f;
            legGroupSize = 3;
            legMoveSpace = 1f;
            legMinLength = .3f;
            legMaxLength = .6f;
            // legExtension = -2f;
            legStraightness = .2f;
            legBaseOffset = 2.5f;
            legPairOffset = 2.5f;
            rippleScale = .15f;
            lockLegBase = true;
            legContinuousMove = true;
            legPhysicsLayer = false;
            allowLegStep = false;

            health = 85f;
            armor = 1f;
            speed = .75f;
            drag = 1f;
            flying = false;
            hitSize = 12f;
            physics = true;
            stepShake = 0f;
            rotateSpeed = 8f;

            createScorch = false;
            createWreck = false;
            
            canAttack = true;
            weapons.add(new Weapon("insect-swarmer") {{
                top = false;
                reload = 25f;
                recoil = -1f;
                x = -.8f;
                y = 4f;
                shootY = 4f;
                shake = .35f;
                alternate = false;
                mirror = true;
                shootSound = Sounds.none;
                bullet = new BasicBulletType() {{
                    damage = 9f;
                    speed = 10f;
                    lifetime = 1f;
                    frontColor = Color.valueOf("#ffffff00");
                    backColor = Color.valueOf("#98ffa900");
                }};
            }});
        }};
        insectSwarmer = new UnitType("insect-swarmer") {{
            aiController = GroundAI::new;
            constructor = LegsUnit::create;
            isEnemy = true;
            groundLayer = 60f;

            legSpeed = .5f;
            legCount = 6;
            legLengthScl = 2f;
            legGroupSize = 3;
            legMoveSpace = .5f;
            legMinLength = .1f;
            legMaxLength = .4f;
            legExtension = 1f;
            legStraightness = .5f;
            legBaseOffset = 1f;
            legPairOffset = .5f;
            rippleScale = .15f;
            lockLegBase = true;
            legContinuousMove = true;
            legPhysicsLayer = false;
            allowLegStep = false;

            health = 12f;
            armor = 0f;
            speed = 1f;
            drag = .5f;
            flying = false;
            hitSize = 4f;
            physics = true;
            stepShake = 0f;
            rotateSpeed = 8f;

            createScorch = false;
            createWreck = false;
            
            canAttack = true;
            weapons.add(new Weapon("insect-swarmer") {{
                top = false;
                reload = 5f;
                recoil = -1f;
                x = -.8f;
                y = 2f;
                shootY = 3f;
                shake = .25f;
                alternate = false;
                mirror = true;
                shootSound = Sounds.none;
                bullet = new BasicBulletType() {{
                    damage = 2f;
                    speed = 10f;
                    lifetime = 1f;
                    frontColor = Color.valueOf("#ffffff00");
                    backColor = Color.valueOf("#98ffa900");
                }};
            }});
        }};
        insectSpit = new UnitType("insect-spit") {{
            aiController = GroundAI::new;
            constructor = LegsUnit::create;
            isEnemy = true;
            groundLayer = 60f;

            legSpeed = 0.7f;
            legCount = 6;
            legLengthScl = 3f;
            legGroupSize = 3;
            legMoveSpace = 0.6f;
            legMinLength = 0.2f;
            legMaxLength = 0.5f;
            legExtension = 1f;
            legStraightness = 0.5f;
            legBaseOffset = 1f;
            legPairOffset = 0.6f;
            rippleScale = 0.15f;
            lockLegBase = true;
            legContinuousMove = true;
            legPhysicsLayer = false;
            allowLegStep = false;

            health = 36f;
            armor = 2f;
            speed = 0.7f;
            drag = 0.5f;
            flying = false;
            hitSize = 12f;
            physics = true;
            stepShake = 0.1f;
            rotateSpeed = 8f;

            createScorch = false;
            createWreck = false;

            canAttack = true;
            weapons.add(new Weapon("insect-spit") {{
                top = false;
                reload = 20f;
                recoil = 1f;
                x = 0f;
                y = 2f;
                shootY = 3f;
                shake = 0.25f;
                alternate = false;
                mirror = false;
                shootSound = Sounds.none;
                bullet = new BasicBulletType(3, 7) {{
                    lifetime = 26f;
                    frontColor = Color.valueOf("#16942e");
                    backColor = Color.valueOf("#16942e");
                    trailColor = Color.valueOf("#16942e");
                    trailWidth = 1.2f;
                    trailLength = 5;
                }};
            }});
        }};
    }
}
