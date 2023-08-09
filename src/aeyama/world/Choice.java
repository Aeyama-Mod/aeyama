package aeyama.world;

import mindustry.type.*;

public class Choice {
    public UnitType unit;
    public Weapon weapon;
    public Cost cost;

    public Choice(UnitType unit) {
        this(unit, null);
    }

    public Choice(Weapon weapon) {
        this(weapon, null);
    }
    
    public Choice(UnitType unit, Cost cost) {
        this.unit = unit;
        this.cost = cost;
    }

    public Choice(Weapon weapon, Cost cost) {
        this.weapon = weapon;
        this.cost = cost;
    }
}