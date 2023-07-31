package aeyama.ui.dialogs;

import arc.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.mod.Mods.*;
import mindustry.ui.dialogs.*;

import static mindustry.Vars.*;
import static aeyama.AeyamaVars.*;

public class NewsDialog extends BaseDialog {
    static LoadedMod mod = Vars.mods.getMod("aeyama");
    String urlNews = "https://raw.githubusercontent.com/" + repo + (isDev ? "dev" : "main") + "/src/assets/news/" + Core.bundle.get("newsFile");
    String urlGithub = "https://github.com/Aeyama-Mod/aeyama";
    String urlDiscord = "https://discord.gg/YVY9Y3uA85";
    String urlProject = "https://github.com/users/FredyJabe/projects/2";

    public NewsDialog() {
        super(Core.bundle.format("title") + Core.bundle.format("installedVersion") + " " + mod.meta.version);

        addCloseListener();
        Table news = getNews();

        onResize(() -> {
            cont.clear();
            loadBody(news);
            loadButtons();
        });
    
        show();
    }

    private void loadBody(Table news) {
        cont.image(Core.atlas.find("aeyama-logo", Core.atlas.find("clear")))
            .height(mobile ? 144f : 185f).width(mobile ? 480f : 620f).pad(3f).center()
            .row();
        
        cont.pane(news).width(mobile ? 480f : 600f)
            .maxWidth(mobile ? 480f : 600f).pad(4f);
    }

    private void loadButtons() {
        //Check if not on mobile Landscape mode
        if (!(mobile && !Core.graphics.isPortrait())) {
            cont.row();
            
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
                t.defaults().size(256f*2f, 64f).pad(3f);
                t.button(Core.bundle.format("linkProject"), Icon.trello, () -> {
                    if (!Core.app.openURI(urlProject)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlProject);
                    }
                }).row();
                t.button("@close", Icon.cancel, this::hide);
            }).center().fillX();
        } else { // If on landscape mobile
            cont.table(t -> {
                t.defaults().size(256f, 64f).pad(3f);
                t.button(Core.bundle.format("linkGithub"), Icon.githubSquare, () -> {
                    if (!Core.app.openURI(urlGithub)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlGithub);
                    }
                }).row();
                t.button(Core.bundle.format("linkDiscord"), Icon.discord, () -> {
                    if (!Core.app.openURI(urlDiscord)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlDiscord);
                    }
                }).row();
                t.button(Core.bundle.format("linkProject"), Icon.trello, () -> {
                    if (!Core.app.openURI(urlProject)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlProject);
                    }
                }).row();
                t.button("@close", Icon.cancel, this::hide);
            }).center().fillX();
        }
    }

    private Table getNews() {
        Table table = new Table();
        Log.info("[green][Aeyama][lightgray] Fetching news...");

        Http.get(urlNews).error(err -> {
            table.add("[red][ERROR] FAILED TO GET NEWS.").center();
            Log.err("[red][Aeyama][lightgray] " + err.getMessage());
        }).block(res -> {
            table.add(res.getResultAsString()).left().growX().wrap().labelAlign(Align.left);
        });

        return table;
    }
}
