const gen = require("aeyamaPlanetGenerator");

// Change the planet generator
Events.on(ContentInitEvent, e => {
    const p = Vars.content.planet("aeyama");
    //p.generator = gen.AeyamaPlanetGenerator;
    p.generator = gen.gen;
});

print("Updated Aeyama planet generator.");