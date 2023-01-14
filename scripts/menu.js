const mod = Vars.mods.getMod("aeyama");
const urlGithub = "https://github.com/FredyJabe/aeyama";
const urlDiscord = "https://discord.gg/rNhkswkJst";
const urlTrello = "https://trello.com/b/MUttXaEk/aeyama-the-new-world";
var aeyamaNews = "";
Http.get(Core.bundle.format("urlNews"), response => {
    aeyamaNews = response.getResultAsString();
});

// Shows news popup window
Events.on(EventType.ClientLoadEvent, e => {
    var dialog = new BaseDialog(Core.bundle.format("modname") + " Installed version: " + mod.meta.version + " " + Core.bundle.format("title"));

    dialog.addCloseListener();// Pressing ESC
    dialog.buttons.defaults().size(192, 64);
    dialog.buttons.button("@close", run(() => {
        dialog.hide();
    })).size(256, 64);

    dialog.cont.pane((() => {
        var table = new Table()

        table.image(Core.atlas.find("aeyama-logo", Core.atlas.find("clear"))).height(185).width(620).pad(3).center(); // Drawing logo
        table.row();
        table.add(aeyamaNews + "\n\n").left().growX().wrap().width(600).maxWidth(600).pad(4).labelAlign(Align.left);
        table.row();

        table.button(Core.bundle.format("linkGithub"), run(() => {
            if (!Core.app.openURI(urlGithub)) {
                Vars.ui.showErrorMessage("@linkfail")
                Core.app.setClipboardText(urlGithub)
            }
        })).size(256, 64);
        table.row();
        table.button(Core.bundle.format("linkTrello"), run(() => {
            if (!Core.app.openURI(urlTrello)) {
                Vars.ui.showErrorMessage("@linkfail")
                Core.app.setClipboardText(urlDiscord)
            }
        })).size(256, 64);
        table.row();
        table.button(Core.bundle.format("linkDiscord"), run(() => {
            if (!Core.app.openURI(urlDiscord)) {
                Vars.ui.showErrorMessage("@linkfail")
                Core.app.setClipboardText(urlDiscord)
            }
        })).size(256, 64);
        table.row();
        
        return table;

    })()).grow().center().maxWidth(800);
    dialog.show();
})
    

// SPECIAL THANKS to Fire mod (https://github.com/Uenhe/Fire)