package aeyama.ui;

import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;

import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;

import static mindustry.Vars.*;

/** An ItemDisplay, but for power. */
public class PowerDisplay extends Table {
    public final float amount;

    public PowerDisplay(float amount) {
        this.amount = amount;

        add(new Stack() {{
            Image image = new Image(Icon.power).setScaling(Scaling.fit);
            image.setColor(Pal.power);
            add(image);

            if(amount != 0) {
                Table t = new Table().left().bottom();
                t.add(Strings.autoFixed(amount, 2)).style(Styles.outlineLabel);
                add(t);
            }
        }}).size(iconMed).padRight(5 * Strings.autoFixed(amount, 2).length());
    }
}
