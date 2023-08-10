package aeyama.content;

import arc.graphics.*;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;

public class AeyamaWeapons {
    public static Weapon
        buildTools, rifle, shotgun, sniperRifle, minigun,
        
        insectJaw, insectJawWeak, insectSpit;
    
    public static void load() {
		buildTools = new Weapon("buildTools") {{
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
		}};
		
		rifle = new Weapon("rifle") {{
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
				frontColor = Color.valueOf("#ffffff");
				backColor = Color.valueOf("#98ffa9");
			}};
		}};
		
		shotgun = new Weapon("minigun") {{
			aiControllable = false;
			top = false;
			x = 5f;
			y = 3f;
			shootY = 2f;
			reload = 40f;
			recoil = 3f;
			shootSound = Sounds.pew;
			shake = .25f;
			alternate = false;
			mirror = false;
			bullet = new BasicBulletType() {{
				damage = 10f;
				speed = 6f;
				lifetime = 15f;
				frontColor = Color.valueOf("#ffffff");
				backColor = Color.valueOf("#98ffa9");
				
				instantDisappear = true;
                fragRandomSpread = 15f;
                fragBullets = 6;
                fragBullet = new BasicBulletType() {{
                    damage = 10f;
                    speed = 4f;
                    lifetime = 20f;
                    width = 2f;
                    height = 2f;
                }};
			}};
		}};
		
		sniperRifle = new Weapon("sniper-rifle") {{
			aiControllable = false;
			top = false;
			x = 5f;
			y = 3f;
			shootY = 2f;
			reload = 60f;
			recoil = 8f;
			shootSound = Sounds.pew;
			shake = .25f;
			alternate = false;
			mirror = false;
			bullet = new BasicBulletType() {{
				damage = 32f;
				speed = 12f;
				lifetime = 20f;
				frontColor = Color.valueOf("#ffffff");
				backColor = Color.valueOf("#98ffa9");
			}};
		}};
		
		minigun = new Weapon("minigun") {{
			aiControllable = false;
			top = false;
			x = 5f;
			y = 3f;
			shootY = 2f;
			reload = 1f;
			recoil = 3f;
			shootSound = Sounds.pew;
			shake = .25f;
			alternate = false;
			mirror = false;
			bullet = new BasicBulletType() {{
				damage = 4f;
				speed = 6f;
				lifetime = 17f;
				frontColor = Color.valueOf("#ffffff");
				backColor = Color.valueOf("#98ffa9");
			}};
		}};
		
		// Insects weapons
		insectJaw = new Weapon("insect-jaw") {{
			top = false;
			reload = 25f;
			recoil = -1f;
			x = -0.8f;
			y = 3.25f;
			shootY = 4f;
			shake = .35f;
			alternate = false;
			mirror = true;
			shootSound = Sounds.none;
			bullet = new BasicBulletType() {{
				damage = 8f;
				speed = 30f;
				lifetime = 1f;
				frontColor = Color.valueOf("#ffffff00");
				backColor = Color.valueOf("#98ffa900");
			}};
		}};
		
		insectJawWeak = new Weapon("insect-jaw-weak") {{
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
				speed = 20f;
				lifetime = 1f;
				frontColor = Color.valueOf("#ffffff00");
				backColor = Color.valueOf("#98ffa900");
			}};
		}};
			
		insectSpit = new Weapon("insect-spit") {{
			top = false;
			reload = 34f;
			recoil = 1f;
			x = 0f;
			y = 2f;
			shootY = 3f;
			shake = 0.25f;
			alternate = false;
			mirror = false;
			shootSound = Sounds.none;
			bullet = new BasicBulletType(5, 7) {{
				status = AeyamaStatusEffects.knockdown;
				statusDuration = 30;
				lifetime = 14f;
				frontColor = Color.valueOf("#16942e");
				backColor = Color.valueOf("#16942e");
				trailColor = Color.valueOf("#16942e");
				trailWidth = 1.2f;
				trailLength = 5;
			}};
		}};
    }
}