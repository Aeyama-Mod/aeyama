package aeyama.ui;

import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;

import mindustry.core.*;
import mindustry.type.*;
import mindustry.ui.*;

/** An ItemDisplay, but for liquids. Copy of Vanilla one to remove the localizedName */
public class ALiquidDisplay extends Table{
    public final Liquid liquid;
    public final float amount;

    public ALiquidDisplay(Liquid liquid, float amount){
        this.liquid = liquid;
        this.amount = amount;

        add(new Stack() {{
            add(new Table(o -> {
                o.left();
                o.add(new Image(liquid.uiIcon)).size(32f).scaling(Scaling.fit);
            }));

            if(amount != 0) {
                add(new Table(t -> {
                    t.left().bottom();
                    t.add(amount >= 1000f ? UI.formatAmount((int) amount) : (int) amount + "").style(Styles.outlineLabel);
                    t.pack();
                }));
            }
        }});
    }
}
