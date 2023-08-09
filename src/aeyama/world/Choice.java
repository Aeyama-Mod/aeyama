package aeyama.world;

import mindustry.type.*;

public class Choice {
        public UnitType unit; //TODO Can be a Weapon
        public Cost cost;

        public Choice(UnitType unit) {
            this(unit, null);
        }

        public Choice(UnitType unit, Cost cost) {
            this.unit = unit;
            this.cost = cost;
        }

        public boolean hasCost() {
            return cost != null;
        }
    }