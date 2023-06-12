package aeyama.ui.dialogs;

import arc.*;
import arc.util.*;

import mindustry.*;
import mindustry.gen.*;
import mindustry.mod.Mods.*;
import mindustry.ui.dialogs.*;

import static mindustry.Vars.*;

public class NewsDialog {
    LoadedMod mod = Vars.mods.getMod("aeyama");
    String urlGithub = "https://github.com/FredyJabe/aeyama";
    String urlDiscord = "https://discord.gg/rNhkswkJst";
    String urlProject = "https://github.com/users/FredyJabe/projects/2";
    // String news = "[red] FAILED TO GET HTTP CONTENT.";

    public NewsDialog() {
        BaseDialog dialog = new BaseDialog(Core.bundle.format("title") + Core.bundle.format("installedVersion") + " " + mod.meta.version);

        dialog.closeOnBack();
        dialog.addCloseListener();
        dialog.buttons.button("@close", Icon.cancel, () -> {
            dialog.hide();
        }).size(256, 64);
        
        Http.get(Core.bundle.format("urlNews"), res -> {
            dialog.cont.image(Core.atlas.find("aeyama-logo", Core.atlas.find("clear"))).height(mobile ? 144 : 185).width(mobile ? 480 : 620).pad(3).center();
            dialog.cont.row();
            dialog.cont.pane(table -> {
                table.add(res.getResultAsString()).left().growX().wrap().width(mobile ? 480 : 600).maxWidth(mobile ? 480 : 600).pad(4).labelAlign(Align.left);
            }).grow().center().maxWidth(600);
            dialog.cont.row();
            dialog.cont.button(Core.bundle.format("linkGithub"), Icon.githubSquare, () -> {
                if (!Core.app.openURI(urlGithub)) {
                    Vars.ui.showErrorMessage("@linkfail");
                    Core.app.setClipboardText(urlGithub);
                }
            }).size(256, 64);
            dialog.cont.row();
            dialog.cont.button(Core.bundle.format("linkDiscord"), Icon.discord, () -> {
                if (!Core.app.openURI(urlGithub)) {
                    Vars.ui.showErrorMessage("@linkfail");
                    Core.app.setClipboardText(urlGithub);
                }
            }).size(256, 64);
            dialog.cont.row();
            dialog.cont.button(Core.bundle.format("linkProject"), Icon.trello, () -> {
                if (!Core.app.openURI(urlProject)) {
                    Vars.ui.showErrorMessage("@linkfail");
                    Core.app.setClipboardText(urlProject);
                }
            }).size(256, 64).center();
            dialog.cont.row();
        });

        dialog.show();
    }
}
