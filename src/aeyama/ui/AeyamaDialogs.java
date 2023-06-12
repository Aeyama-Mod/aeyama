package aeyama.ui;

import aeyama.ui.dialogs.*;

public class AeyamaDialogs {
    public static NewsDialog newsDialog;
    
    public static void load() {
        newsDialog = new NewsDialog();
    }
}
