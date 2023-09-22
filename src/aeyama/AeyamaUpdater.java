package aeyama;

import arc.*;
import arc.files.*;
import arc.input.*;
import arc.util.*;
import arc.util.Http.*;
import arc.util.io.*;
import arc.util.serialization.*;
import arc.util.serialization.Jval.*;

import mindustry.ui.dialogs.*;

import java.net.*;

import static aeyama.AeyamaVars.*;
import static arc.Core.*;
import static mindustry.Vars.*;

/** Most of this is from the mod "Scheme Size". */
public class AeyamaUpdater {
    public static String urlReleases = ghApi + "/repos/" + repo + "/releases";
    public static String betaDownload;
    public static String download;
    public static float progress;

    public static void check() {
        Log.info("[green][Aeyama][lightgray] Checking for updates...");

        Http.get(urlReleases, res -> {
            JsonArray releases = Jval.read(res.getResultAsString()).asArray();
            Jval release;

            // Check if the first release is a beta (pre-release) only if the user wants
            if (settings.getBool("aeyama-checkBetaUpdate") && (release = releases.get(0)).getBool("prerelease", false)) {
                String betaVersion = release.getString("tag_name").substring(1);
                betaDownload = release.get("assets").asArray().get(0).getString("browser_download_url");
                
                // Get the first release, skip all the pre-release
                for (int i=1; i<releases.size; i++) {
                    if (!releases.get(i).getBool("prerelease", false)) {
                        release = releases.get(i);
                        break;
                    }
                }
                String version = release.getString("tag_name").substring(1);
                download = release.get("assets").asArray().get(0).getString("browser_download_url");

                if (!version.equals(mod.meta.version) || !betaVersion.equals(mod.meta.version))
                    showCustomConfirmBeta(
                        "@aeyama.updater.name", Core.bundle.format("aeyama.updater.info-beta", mod.meta.version, version, betaVersion),
                        "@aeyama.updater.install", "@aeyama.updater.install-beta", "@aeyama.updater.ignore",
                        AeyamaUpdater::update, AeyamaUpdater::updateBeta
                    );
                } else { // The first release is the latest
                    release = releases.get(0);
                    // Check if it's not a prerelease, if it is then get the firs release
                    if (release.getBool("prerelease", false)) {
                        for (int i=1; i < releases.size; i++) {
                            if (!releases.get(i).getBool("prerelease", false)) {
                                release = releases.get(i);
                                break;
                            }
                        }
                    }
                    String version = release.getString("tag_name").substring(1);
                    download = release.get("assets").asArray().get(0).getString("browser_download_url");
    
                    if (!version.equals(mod.meta.version))
                        showCustomConfirm(
                            "@aeyama.updater.name", Core.bundle.format("aeyama.updater.info", mod.meta.version, version),
                            "@aeyama.updater.install", "@aeyama.updater.ignore",
                            AeyamaUpdater::update
                        );

            }
        }, Log::err);
    }

    public static void update() {
        try {
            if (mod.loader instanceof URLClassLoader cl) cl.close();
            mod.loader = null;
        } catch (Throwable err) {
            Log.err(err);
        }
        
        ui.loadfrag.show("@downloading");
        ui.loadfrag.setProgress(() -> progress);

        Http.get(download, AeyamaUpdater::handle, Log::err);
    }

    public static void updateBeta() {
        try {
            if (mod.loader instanceof URLClassLoader cl) cl.close();
            mod.loader = null;
        } catch (Throwable err) {
            Log.err(err);
        }
        
        ui.loadfrag.show("@downloading");
        ui.loadfrag.setProgress(() -> progress);

        Http.get(betaDownload, AeyamaUpdater::handle, Log::err);
    }

    public static void handle(HttpResponse res) {
        try {
            Fi file = tmpDirectory.child(repo.replace("/", "") + ".zip");
            Streams.copyProgress(res.getResultAsStream(), file.write(false), res.getContentLength(), 4096, p -> progress = p);
            
            mods.importMod(file).setRepo(repo);
            file.delete();

            Core.app.post(ui.loadfrag::hide);
            ui.showInfoOnHidden("@mods.reloadexit", Core.app::exit);
        } catch (Throwable err) {
            Log.err(err);
        }
    }

    /*
     * Custom "showCustomComfirm" for the Updater UI only.
     */

    public static void showCustomConfirm(String title, String text, String yes, String no, Runnable confirmed){
        BaseDialog dialog = new BaseDialog(title);
        dialog.cont.add(text).width(mobile ? 400f : 500f).wrap().pad(4f).get().setAlignment(Align.center, Align.center);
        dialog.buttons.defaults().size(125f, 54f).pad(2f);
        dialog.setFillParent(false);
        dialog.buttons.button(yes, () -> {
            dialog.hide();
            confirmed.run();
        });
        dialog.buttons.button(no, dialog::hide);
        dialog.keyDown(KeyCode.escape, dialog::hide);
        dialog.keyDown(KeyCode.back, dialog::hide);
        dialog.show();
    }

    public static void showCustomConfirmBeta(String title, String text, String yes1, String yes2, String no, Runnable confirmed1, Runnable confirmed2){
        BaseDialog dialog = new BaseDialog(title);
        dialog.cont.add(text).width(mobile ? 400f : 500f).wrap().pad(4f).get().setAlignment(Align.center, Align.center);
        dialog.buttons.defaults().size(250f, 54f).pad(2f);
        dialog.setFillParent(false);
        dialog.buttons.table(t -> {
            t.defaults().size(125f, 54f).pad(2f);
            t.button(yes1, () -> {
                dialog.hide();
                confirmed1.run();
            });
            t.button(yes2, () -> {
                dialog.hide();
                confirmed2.run();
            });
        }).row();
        dialog.buttons.button(no, dialog::hide);
        dialog.keyDown(KeyCode.escape, dialog::hide);
        dialog.keyDown(KeyCode.back, dialog::hide);
        dialog.show();
    }
}
