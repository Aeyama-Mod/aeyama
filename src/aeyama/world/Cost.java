package aeyama.world;

import mindustry.type.*;

public class Cost {
        public ItemStack[] items;
        public LiquidStack[] liquids;
        public float power = 0f;
        public float heat = 0f;
        public float time = 0f;

        public Cost(float time) {
            this(ItemStack.empty, LiquidStack.empty, 0f, 0f, time);
        }
        
        public Cost(ItemStack[] items, float time) {
            this(items, LiquidStack.empty, 0f, 0f, time);
        }

        public Cost(ItemStack[] items, LiquidStack[] liquids, float time) {
            this(items, liquids, 0f, 0f, time);
        }

        public Cost(ItemStack[] items, LiquidStack[] liquids, float power, float time) {
            this(items, liquids, power, 0f, time);
        }

        public Cost(ItemStack[] items, LiquidStack[] liquids, float power, float heat, float time) {
            this.items = items;
            this.liquids = liquids;
            this.power = power;
            this.heat = heat;
            this.time = time;
        }

        public boolean hasItems() {
            return items.length > 0;
        }

        public boolean hasLiquids() {
            return liquids.length > 0;
        }

        public boolean hasPower() {
            return power > 0f;
        }

        public boolean hasHeat() {
            return heat > 0f;
        }

        public boolean allEmtpy() {
            return !hasItems() && !hasLiquids() && !hasPower() && !hasHeat();
        }
    }
