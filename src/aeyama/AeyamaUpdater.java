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
    private static final String urlReleases = ghApi + "/repos/" + repo + "/releases";
    private static float progress;

    public static void check() {
        if (isDev) {
            Log.info("[green][Aeyama][lightgray] Not checking for update on development version.");
            return;
        }

        Log.info("[green][Aeyama][lightgray] Checking for updates...");

        // x.y.z-__.w --> xyz and w
        int modVersionCheck = Integer.parseInt(mod.meta.version.substring(0, 5).replace(".", ""));
        // Check if the mod version has a build number (only with -beta or -pre version)
        int modBuild = isBeta ? Integer.parseInt(mod.meta.version.substring(mod.meta.version.length() - 1)) : 0;

        // Everything has to be in this HTTP request to work (async)
        Http.get(urlReleases, res -> {
            /* Get the releases */
            JsonArray releases = Jval.read(res.getResultAsString()).asArray();
            Jval release = null;
            Jval betaRelease = null;
            boolean uiShown = false;

            // Check if the first release is a "pre-release" (beta)
            if (releases.get(0).getBool("prerelease", false)) {
                betaRelease = releases.get(0);
                // Get the first non "pre-release" (release)
                for (int i=1; i < releases.size; i++) {
                    // Check if the release is a "pre-release", skip.
                    if (!releases.get(i).getBool("prerelease", false)) {
                        release = releases.get(i);
                        break;
                    }
                }
            } else { // The first release is not beta, ignore beta
                release = releases.get(0);
                betaRelease = null;
            }

            String releaseVersion = release.getString("tag_name").substring(1); // Get version of release without the 'v'
            
            if (betaRelease != null && settings.getBool("aeyama-checkBeta")) { // There's a beta to check
                // vx.y.z-__.w --> xyz and w    
                String betaVersion = betaRelease.getString("tag_name").substring(1);
                int betaVersionCheck = Integer.parseInt(betaVersion.substring(0, 5).replace(".", ""));
                int betaBuild = Integer.parseInt(betaVersion.substring(betaVersion.length() - 1));

                //Check if the beta version is newer than the installed one or that the installed version build number is greater
                if (betaVersionCheck > modVersionCheck
                    || (betaVersionCheck == modVersionCheck && betaBuild > modBuild)) {
                    String betaDownloadUrl = betaRelease.get("assets").asArray().get(0).getString("browser_download_url");
                    String downloadUrl = release.get("assets").asArray().get(0).getString("browser_download_url");
                    showCustomConfirmBeta(
                        "@aeyama.updater.name", Core.bundle.format("aeyama.updater.info-beta", mod.meta.version, releaseVersion, betaVersion),
                        "@aeyama.updater.install", "@aeyama.updater.install-beta", "@aeyama.updater.ignore",
                        () -> AeyamaUpdater.update(downloadUrl), () -> AeyamaUpdater.update(betaDownloadUrl)
                    );
                    uiShown = true;
                }
            }

            // The UI for beta update has not be shown (so no beta or setting disabled)
            if (uiShown != true) {
                // x.y.z --> xyz
                int releaseVersionCheck = Integer.parseInt(releaseVersion.substring(0, 5).replace(".", ""));

                //Check if the release version is newer than the installed one or that the installed version is a beta/pre
                if (releaseVersionCheck > modVersionCheck
                    || (releaseVersionCheck == modVersionCheck && isBeta)) {
                    String downloadUrl = release.get("assets").asArray().get(0).getString("browser_download_url");
                    showCustomConfirm(
                        "@aeyama.updater.name", Core.bundle.format("aeyama.updater.info", mod.meta.version, releaseVersion),
                        "@aeyama.updater.install", "@aeyama.updater.ignore",
                        () -> AeyamaUpdater.update(downloadUrl)
                    );
                }
            }
        }, Log::err);
    }

    private static void update(String url) {
        try {
            if (mod.loader instanceof URLClassLoader cl) cl.close();
            mod.loader = null;
        } catch (Throwable err) {
            Log.err(err);
        }
        
        ui.loadfrag.show("@downloading");
        ui.loadfrag.setProgress(() -> progress);

        Http.get(url, AeyamaUpdater::handle, Log::err);
    }

    private static void handle(HttpResponse res) {
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

    /* Custom "showCustomComfirm" for the Updater UI only. */
    private static void showCustomConfirm(String title, String text, String yes, String no, Runnable confirmed) {
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

    private static void showCustomConfirmBeta(String title, String text, String yes1, String yes2, String no, Runnable confirmed1, Runnable confirmed2) {
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
