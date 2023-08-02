package aeyama.ui;

import arc.graphics.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;

import mindustry.gen.*;
import mindustry.ui.*;

import static mindustry.Vars.*;

/** An ItemDisplay, but for heat. */
public class HeatDisplay extends Table {
    public final float amount;

    public HeatDisplay(float amount) {
        this.amount = amount;

        add(new Stack() {{
            Image image = new Image(Icon.waves).setScaling(Scaling.fit);
            image.setColor(new Color(1f, 0.22f, 0.22f, 0.8f));
            add(image);

            if(amount != 0) {
                Table t = new Table().left().bottom();
                t.add(Strings.autoFixed(amount, 2)).style(Styles.outlineLabel);
                t.pack();
                add(t);
            }
        }}).size(iconMed).padRight(5 * Strings.autoFixed(amount, 2).length());
    }
}
