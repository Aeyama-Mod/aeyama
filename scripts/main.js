//const gen = require("AeyamaPlanetGenerator");

// Creates new attributes
Attribute.add("wood");
Attribute.add("iron");
Attribute.add("zinc");
Attribute.add("copper");

// Then apply some changes
Events.on(ContentInitEvent, e => {
    // Change the planet generator
    // Using ErekirPlanetGenerator prevents people to generate random sectors
    const p = Vars.content.planet("aeyama");
    p.generator = new ErekirPlanetGenerator();

    // Adds ores attributes to floor blocks, resources are everywhere.
    Vars.content.block("aeyama-tree").attributes.set(Attribute.get("wood"), 1);
    Vars.content.block("aeyama-ore-iron").attributes.set(Attribute.get("iron"), 0.25);
    Vars.content.block("aeyama-ore-zinc").attributes.set(Attribute.get("zinc"), 0.25);
    Vars.content.block("aeyama-ore-new-copper").attributes.set(Attribute.get("copper"), 0.25);
    Vars.content.block("aeyama-floor-grassa").attributes.set(Attribute.get("iron"), 0.0125);
    Vars.content.block("aeyama-floor-grassa").attributes.set(Attribute.get("zinc"), 0.0125);
    Vars.content.block("aeyama-floor-grassa").attributes.set(Attribute.get("copper"), 0.0125);
    Vars.content.block("aeyama-floor-grassb").attributes.set(Attribute.get("iron"), 0.0125);
    Vars.content.block("aeyama-floor-grassb").attributes.set(Attribute.get("zinc"), 0.0125);
    Vars.content.block("aeyama-floor-grassb").attributes.set(Attribute.get("copper"), 0.0125);
    Vars.content.block("aeyama-floor-grassc").attributes.set(Attribute.get("iron"), 0.0125);
    Vars.content.block("aeyama-floor-grassc").attributes.set(Attribute.get("zinc"), 0.0125);
    Vars.content.block("aeyama-floor-grassc").attributes.set(Attribute.get("copper"), 0.0125);

    // Adds the attributes to the miners and harvesters
    Vars.content.block("aeyama-wood-harvester").attribute = Attribute.get("wood");
    Vars.content.block("aeyama-iron-miner").attribute = Attribute.get("iron");
    Vars.content.block("aeyama-zinc-miner").attribute = Attribute.get("zinc");
    Vars.content.block("aeyama-copper-miner").attribute = Attribute.get("copper");
});