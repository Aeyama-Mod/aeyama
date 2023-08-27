package aeyama.world;

import mindustry.type.*;

public class Choice {
    //TODO Make it more global maybe?
    public UnitType armor;
    public Weapon weapon;
    public Cost cost;

    public Choice(UnitType armor) {
        this(armor, null);
    }

    public Choice(Weapon weapon) {
        this(weapon, null);
    }
    
    public Choice(UnitType armor, Cost cost) {
        this.armor = armor;
        this.cost = cost;
    }

    public Choice(Weapon weapon, Cost cost) {
        this.weapon = weapon;
        this.cost = cost;
    }
}