Events.on(ContentInitEvent, e => {
    // Adds ores attributes to floor blocks, resources are everywhere.
    Vars.content.block("aeyama-tree").attributes.set(Attribute.get("wood"), 1);
    Vars.content.block("aeyama-floor-water").attributes.set(Attribute.get("water"), 1);
    // Iron
    Vars.content.block("aeyama-floor-ore-iron").attributes.set(Attribute.get("iron"), 0.25);
    Vars.content.block("aeyama-floor-ore-zinc").attributes.set(Attribute.get("iron"), 0.075);
    Vars.content.block("aeyama-floor-ore-new-copper").attributes.set(Attribute.get("iron"), 0.075);
    Vars.content.block("aeyama-floor-grassa").attributes.set(Attribute.get("iron"), 0.025);
    Vars.content.block("aeyama-floor-grassb").attributes.set(Attribute.get("iron"), 0.025);
    Vars.content.block("aeyama-floor-grassc").attributes.set(Attribute.get("iron"), 0.025);
    Vars.content.block("aeyama-floor-grassd").attributes.set(Attribute.get("iron"), 0.025);
    Vars.content.block("aeyama-floor-dirta").attributes.set(Attribute.get("iron"), 0.05);
    Vars.content.block("aeyama-floor-dirtb").attributes.set(Attribute.get("iron"), 0.05);
    Vars.content.block("aeyama-floor-dirtc").attributes.set(Attribute.get("iron"), 0.05);
    Vars.content.block("aeyama-floor-stonea").attributes.set(Attribute.get("iron"), 0.125);
    Vars.content.block("aeyama-floor-stoneb").attributes.set(Attribute.get("iron"), 0.125);
    Vars.content.block("aeyama-floor-stonec").attributes.set(Attribute.get("iron"), 0.125);
    // Zinc
    Vars.content.block("aeyama-floor-ore-iron").attributes.set(Attribute.get("zinc"), 0.075);
    Vars.content.block("aeyama-floor-ore-zinc").attributes.set(Attribute.get("zinc"), 0.25);
    Vars.content.block("aeyama-floor-ore-new-copper").attributes.set(Attribute.get("zinc"), 0.075);
    Vars.content.block("aeyama-floor-grassa").attributes.set(Attribute.get("zinc"), 0.025);
    Vars.content.block("aeyama-floor-grassb").attributes.set(Attribute.get("zinc"), 0.025);
    Vars.content.block("aeyama-floor-grassc").attributes.set(Attribute.get("zinc"), 0.025);
    Vars.content.block("aeyama-floor-grassd").attributes.set(Attribute.get("zinc"), 0.025);
    Vars.content.block("aeyama-floor-dirta").attributes.set(Attribute.get("zinc"), 0.05);
    Vars.content.block("aeyama-floor-dirtb").attributes.set(Attribute.get("zinc"), 0.05);
    Vars.content.block("aeyama-floor-dirtc").attributes.set(Attribute.get("zinc"), 0.05);
    Vars.content.block("aeyama-floor-stonea").attributes.set(Attribute.get("zinc"), 0.125);
    Vars.content.block("aeyama-floor-stoneb").attributes.set(Attribute.get("zinc"), 0.125);
    Vars.content.block("aeyama-floor-stonec").attributes.set(Attribute.get("zinc"), 0.125);
    // Copper
    Vars.content.block("aeyama-floor-ore-iron").attributes.set(Attribute.get("copper"), 0.075);
    Vars.content.block("aeyama-floor-ore-zinc").attributes.set(Attribute.get("copper"), 0.075);
    Vars.content.block("aeyama-floor-ore-new-copper").attributes.set(Attribute.get("copper"), 0.25);
    Vars.content.block("aeyama-floor-grassa").attributes.set(Attribute.get("copper"), 0.025);
    Vars.content.block("aeyama-floor-grassb").attributes.set(Attribute.get("copper"), 0.025);
    Vars.content.block("aeyama-floor-grassc").attributes.set(Attribute.get("copper"), 0.025);
    Vars.content.block("aeyama-floor-grassd").attributes.set(Attribute.get("copper"), 0.025);
    Vars.content.block("aeyama-floor-dirta").attributes.set(Attribute.get("copper"), 0.05);
    Vars.content.block("aeyama-floor-dirtb").attributes.set(Attribute.get("copper"), 0.05);
    Vars.content.block("aeyama-floor-dirtc").attributes.set(Attribute.get("copper"), 0.05);
    Vars.content.block("aeyama-floor-stonea").attributes.set(Attribute.get("copper"), 0.125);
    Vars.content.block("aeyama-floor-stoneb").attributes.set(Attribute.get("copper"), 0.125);
    Vars.content.block("aeyama-floor-stonec").attributes.set(Attribute.get("copper"), 0.125);
    // Stone (also has walls)
    Vars.content.block("aeyama-floor-ore-iron").attributes.set(Attribute.get("stone"), 0.125);
    Vars.content.block("aeyama-floor-ore-zinc").attributes.set(Attribute.get("stone"), 0.125);
    Vars.content.block("aeyama-floor-ore-new-copper").attributes.set(Attribute.get("stone"), 0.125);
    Vars.content.block("aeyama-floor-grassa").attributes.set(Attribute.get("stone"), 0.025);
    Vars.content.block("aeyama-floor-grassb").attributes.set(Attribute.get("stone"), 0.025);
    Vars.content.block("aeyama-floor-grassc").attributes.set(Attribute.get("stone"), 0.025);
    Vars.content.block("aeyama-floor-grassd").attributes.set(Attribute.get("stone"), 0.025);
    Vars.content.block("aeyama-floor-dirta").attributes.set(Attribute.get("stone"), 0.05);
    Vars.content.block("aeyama-floor-dirtb").attributes.set(Attribute.get("stone"), 0.05);
    Vars.content.block("aeyama-floor-dirtc").attributes.set(Attribute.get("stone"), 0.05);
    Vars.content.block("aeyama-floor-stonea").attributes.set(Attribute.get("stone"), 0.25);
    Vars.content.block("aeyama-floor-stoneb").attributes.set(Attribute.get("stone"), 0.25);
    Vars.content.block("aeyama-floor-stonec").attributes.set(Attribute.get("stone"), 0.25);
    Vars.content.block("aeyama-wall-dirta").attributes.set(Attribute.get("stone"), 0.1);
    Vars.content.block("aeyama-wall-dirtb").attributes.set(Attribute.get("stone"), 0.1);
    Vars.content.block("aeyama-wall-dirtc").attributes.set(Attribute.get("stone"), 0.1);
    Vars.content.block("aeyama-wall-stonea").attributes.set(Attribute.get("stone"), 0.25);
    Vars.content.block("aeyama-wall-stoneb").attributes.set(Attribute.get("stone"), 0.25);
    Vars.content.block("aeyama-wall-stonec").attributes.set(Attribute.get("stone"), 0.25);

    // Adds the attributes to the miners and harvesters
    Vars.content.block("aeyama-wood-harvester").attribute = Attribute.get("wood");
    Vars.content.block("aeyama-iron-miner").attribute = Attribute.get("iron");
    Vars.content.block("aeyama-zinc-miner").attribute = Attribute.get("zinc");
    Vars.content.block("aeyama-copper-miner").attribute = Attribute.get("copper");
    Vars.content.block("aeyama-stone-miner").attribute = Attribute.get("stone");

    // Makes sure the new water is animated
    Vars.content.block("aeyama-floor-water").cacheLayer = CacheLayer.water;
});