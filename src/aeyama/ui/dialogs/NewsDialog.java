package aeyama.ui.dialogs;

import arc.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.mod.Mods.*;
import mindustry.ui.dialogs.*;

import static mindustry.Vars.*;

public class NewsDialog extends BaseDialog {
    static LoadedMod mod = Vars.mods.getMod("aeyama");
    String urlGithub = "https://github.com/Aeyama-Mod/aeyama";
    String urlDiscord = "https://discord.gg/YVY9Y3uA85";
    String urlProject = "https://github.com/users/FredyJabe/projects/2";

    public NewsDialog() {
        super(Core.bundle.format("title") + Core.bundle.format("installedVersion") + " " + mod.meta.version);

        addCloseListener();
        buttons.button("@close", Icon.cancel, this::hide).size(256f, 64f);

        cont.image(Core.atlas.find("aeyama-logo", Core.atlas.find("clear")))
            .height(mobile ? 144f : 185f).width(mobile ? 480f : 620f).pad(3f).center()
            .row();
        cont.pane(getNews()).width(mobile ? 480f : 600f)
            .maxWidth(mobile ? 480f : 600f).pad(4f).row();
        
        cont.table(t -> {
            t.defaults().size(256f, 64f).pad(3f);
            t.button(Core.bundle.format("linkGithub"), Icon.githubSquare, () -> {
                if (!Core.app.openURI(urlGithub)) {
                    Vars.ui.showErrorMessage("@linkfail");
                    Core.app.setClipboardText(urlGithub);
                }
            });
            t.button(Core.bundle.format("linkDiscord"), Icon.discord, () -> {
                if (!Core.app.openURI(urlDiscord)) {
                    Vars.ui.showErrorMessage("@linkfail");
                    Core.app.setClipboardText(urlDiscord);
                }
            }).row();
        }).center().fillX().row();
        cont.table(t -> {
            t.button(Core.bundle.format("linkProject"), Icon.trello, () -> {
                if (!Core.app.openURI(urlProject)) {
                    Vars.ui.showErrorMessage("@linkfail");
                    Core.app.setClipboardText(urlProject);
                }
            }).size(256f*2f, 64f);
        }).center().fillX();

        show();
    }

    private Table getNews() {
        Table table = new Table();

        Http.get(Core.bundle.format("urlNews")).error(err -> {
            table.add("[red][ERROR] FAILED TO GET NEWS.").center();
            Log.err(err.getMessage());
        }).block(res -> {
            table.add(res.getResultAsString()).left().growX().wrap().labelAlign(Align.left);
        });

        return table;
    }
}
