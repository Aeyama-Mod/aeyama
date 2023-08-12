package aeyama.world;

import arc.struct.*;

import mindustry.type.*;

public class Cost {
    public ItemStack[] items;
    public LiquidStack[] liquids;
    public float power = 0f;
    public float heat = 0f;
    public float time = 0f;

    public ObjectSet<Item> itemsUnique = new ObjectSet<>();
    public ObjectSet<Liquid> liquidsUnique = new ObjectSet<>();

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

        if (hasItems()) for (ItemStack item : items)
            itemsUnique.add(item.item);
        if (hasLiquids()) for (LiquidStack liquid : liquids)
            liquidsUnique.add(liquid.liquid);
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

    public int maxItemAmount() {
        int max = 0;
        for(ItemStack item : items)
            max = Math.max(item.amount, max);
        return max;
    }

    public float maxLiquidAmount() {
        float max = 0;
        for(LiquidStack liquid : liquids)
            max = Math.max(liquid.amount, max);
        return max;
    }
}
