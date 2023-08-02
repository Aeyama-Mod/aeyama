package aeyama.ui;

import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.type.*;
import mindustry.ui.*;

import static mindustry.Vars.*;

/** An ItemDisplay, but for liquids. Copy of Vanilla one to remove the localizedName */
public class ALiquidDisplay extends Table{
    public final Liquid liquid;
    public final float amount;

    public ALiquidDisplay(Liquid liquid, float amount){
        this.liquid = liquid;
        this.amount = amount;

        add(new Stack(){{
            add(new Image(liquid.uiIcon).setScaling(Scaling.fit));

            if(amount != 0){
                Table t = new Table().left().bottom();
                t.add(Strings.autoFixed(amount, 2)).style(Styles.outlineLabel);
                add(t);
            }
        }}).size(iconMed).padRight(5 * Strings.autoFixed(amount, 2).length());
    }
}
