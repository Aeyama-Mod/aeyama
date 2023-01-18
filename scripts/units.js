Events.on(ContentInitEvent, e => {
    // Makes sure that enemies can't go over walls
    Vars.content.unit("aeyama-unit-insect-swarmer").allowLegStep = false;
    Vars.content.unit("aeyama-unit-insect-crawler").allowLegStep = false;
});