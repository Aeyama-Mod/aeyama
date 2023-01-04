// Creates new attributes
Attribute.add("wood");


Events.on(ContentInitEvent, e => {
    // Change the planet generator
    // Using ErekirPlanetGenerator prevents people to generate random sectors
    const p = Vars.content.planet("aeyama");
    p.generator = new ErekirPlanetGenerator();

    // Adds the new attribute to trees to be able to harvest
    let t = Vars.content.block("aeyama-tree");
    t.attributes.set(Attribute.get("wood"), 1);
    let d = Vars.content.block("aeyama-wood-harvester");
    d.attribute = Attribute.get("wood");
});