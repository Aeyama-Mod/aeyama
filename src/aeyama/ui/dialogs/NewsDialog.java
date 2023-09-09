package aeyama.ui.dialogs;

import arc.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.ui.dialogs.*;

import static mindustry.Vars.*;
import static aeyama.AeyamaVars.*;

public class NewsDialog {
    static BaseDialog dialog;
    static String urlNews = "https://raw.githubusercontent.com/" + repo + (isDev ? "/dev" : "/main") + "/src/assets/news/" + Core.bundle.get("aeyama.news.file");
    static String urlGitHub = "https://github.com/Aeyama-Mod/aeyama";
    static String urlDiscord = "https://discord.gg/YVY9Y3uA85";
    static String urlProject = "https://github.com/users/FredyJabe/projects/2";

    public static void load() {
        dialog = new BaseDialog(Core.bundle.format("aeyama.news.title", mod.meta.version));

        dialog.addCloseListener();
        Table news = getNews();

        onResize(() -> {
            dialog.cont.clear();
            loadBody(news);
            loadButtons();
        });
    
        dialog.show();
    }

    private static void loadBody(Table news) {
        dialog.cont.image(Core.atlas.find("aeyama-logo", Core.atlas.find("clear")))
            .height(mobile ? 144f : 185f).width(mobile ? 480f : 620f).pad(3f).center()
            .row();
        
        dialog.cont.pane(news).width(mobile ? 480f : 600f)
            .maxWidth(mobile ? 480f : 600f).pad(4f);
    }

    private static void loadButtons() {
        //Check if not on mobile Landscape mode
        if (!(mobile && !Core.graphics.isPortrait())) {
            dialog.cont.row();
            
            dialog.cont.table(t -> {
                t.defaults().size(256f, 64f).pad(3f);
                t.button("@aeyama.news.github", Icon.githubSquare, () -> {
                    if (!Core.app.openURI(urlGitHub)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlGitHub);
                    }
                });
                t.button("@aeyama.news.discord", Icon.discord, () -> {
                    if (!Core.app.openURI(urlDiscord)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlDiscord);
                    }
                }).row();
            }).center().fillX().row();
            dialog.cont.table(t -> {
                t.defaults().size(256f*2f, 64f).pad(3f);
                t.button("@aeyama.news.project", Icon.trello, () -> {
                    if (!Core.app.openURI(urlProject)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlProject);
                    }
                }).row();
                t.button("@close", Icon.cancel, dialog::hide);
            }).center().fillX();
        } else { // If on landscape mobile
            dialog.cont.table(t -> {
                t.defaults().size(256f, 64f).pad(3f);
                t.button("@aeyama.news.github", Icon.githubSquare, () -> {
                    if (!Core.app.openURI(urlGitHub)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlGitHub);
                    }
                }).row();
                t.button("@aeyama.news.discord", Icon.discord, () -> {
                    if (!Core.app.openURI(urlDiscord)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlDiscord);
                    }
                }).row();
                t.button("@aeyama.news.project", Icon.trello, () -> {
                    if (!Core.app.openURI(urlProject)) {
                        Vars.ui.showErrorMessage("@linkfail");
                        Core.app.setClipboardText(urlProject);
                    }
                }).row();
                t.button("@close", Icon.cancel, dialog::hide);
            }).center().fillX();
        }
    }

    private static Table getNews() {
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

    /* Copy of the method because it's protected */
    private static void onResize(Runnable run){
        Events.on(ResizeEvent.class, event -> {
            if(dialog.isShown() && Core.scene.getDialog() == dialog){
                run.run();
                dialog.updateScrollFocus();
            }
        });
    }
}
