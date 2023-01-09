// Change the planet generator
// Using ErekirPlanetGenerator prevents people to generate random sectors
Events.on(ContentInitEvent, e => {
    Vars.content.planet("aeyama").generator = new ErekirPlanetGenerator();
});