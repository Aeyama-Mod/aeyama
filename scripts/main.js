// Creates new attributes
Attribute.add("wood");

// Then apply some changes
Events.on(ContentInitEvent, e => {
    // Change the planet generator
    // Using ErekirPlanetGenerator prevents people to generate random sectors
    const p = Vars.content.planet("aeyama");
    p.generator = new ErekirPlanetGenerator();

    // Adds the new attribute to trees to be able to harvest
    Vars.content.block("aeyama-tree").attributes.set(Attribute.get("wood"), 1);
    Vars.content.block("aeyama-wood-harvester").attribute = Attribute.get("wood");
});