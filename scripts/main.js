// Creates new attributes
Attribute.add("wood");

// Then apply some changes
Events.on(ContentInitEvent, e => {
    // Change the planet generator
    // Using ErekirPlanetGenerator prevents people to generate random sectors
    const p = Vars.content.planet("aeyama");
    p.generator = new ErekirPlanetGenerator();
    p.generator.heightScl = 0.9;//f 0.9
    p.generator.octaves = 8;//f 8
    p.generator.persistence = 0.7;//f 0.7
    p.generator.heightPow = 3;//f 3
    p.generator.heightMult = 1.6;//f 1.6
    p.generator.arkThresh = 0.28;//f 0.28
    p.generator.arkScl = 0.83;//f 0.83
    p.generator.arkSeed = 7;//i 7
    p.generator.arkOct = 2;//i 2
    p.generator.liqThresh = 0.64;//f 0.64
    p.generator.liqScl = 87;//f 87
    p.generator.redThresh = 3.1;//f 3.1
    p.generator.noArkThresh = 0.3;//f 0.3
    p.generator.crystalSeed = 8;//i 8
    p.generator.crystalOct = 2;//i 2
    p.generator.crystalScl = 0.9;//f 0.9
    p.generator.crystalMag = 0.3;//f 0.3
    p.generator.airThresh = 0.13;//f 0.13
    p.generator.airScl = 14;//f 14

    // Adds the new attribute to trees to be able to harvest
    Vars.content.block("aeyama-tree").attributes.set(Attribute.get("wood"), 1);
    Vars.content.block("aeyama-wood-harvester").attribute = Attribute.get("wood");
});