package aeyama.ui;

import arc.graphics.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;

import mindustry.core.*;
import mindustry.gen.*;
import mindustry.ui.*;

/** An ItemDisplay, but for heat. */
public class HeatDisplay extends Table {
    public final float amount;

    public HeatDisplay(float amount) {
        this.amount = amount;

        add(new Stack() {{
            add(new Table(o -> {
                o.left();
                o.add(new Image(Icon.waves)).size(32f).scaling(Scaling.fit).color(new Color(1f, 0.22f, 0.22f, 0.8f));
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
