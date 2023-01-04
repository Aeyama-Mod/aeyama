// Creates new attributes
Attribute.add("wood");

// Then apply some changes
Events.on(ContentInitEvent, e => {
    // Change the planet generator
    // Using ErekirPlanetGenerator prevents people to generate random sectors
    const p = Vars.content.planet("aeyama");
    p.generator = new ErekirPlanetGenerator();
    p.generator.heightScl = 0.9;//f
    p.generator.octaves = 8;//f
    p.generator.persistance = 0.7;//f
    p.generator.heightPow = 3;//f
    p.generator.heightMult = 1.6;//f
    p.generator.arkTresh = 0.28;//f
    p.generator.arkScl = 0.83;//f
    p.generator.arkSeed = 7;//i
    p.generator.arkOct = 2;//i
    p.generator.liqTresh = 0.64;//f
    p.generator.liqScl = 87;//f
    p.generator.redTresh = 3.1;//f
    p.generator.noArkTresh = 0.3;//f
    p.generator.crystalSeed = 8;//i
    p.generator.crystalOct = 2;//i
    p.generator.crystalScl = 0.9;//f
    p.generator.crystalMag = 0.3;//f
    p.generator.airTresh = 0.13;//f
    p.generator.airScl = 14;//f

    // Adds the new attribute to trees to be able to harvest
    Vars.content.block("aeyama-tree").attributes.set(Attribute.get("wood"), 1);
    Vars.content.block("aeyama-wood-harvester").attribute = Attribute.get("wood");
});