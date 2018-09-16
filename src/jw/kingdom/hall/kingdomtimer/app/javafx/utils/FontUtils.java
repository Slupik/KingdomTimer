package jw.kingdom.hall.kingdomtimer.app.javafx.utils;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class FontUtils {

    public static double findFontSizeForHeight(Font font, String text, int maxHeight) {
        Font unitFont = new Font(font.getName(), 1);
        double unitSize = textHeight(unitFont, text);
        return maxHeight/unitSize;
    }

    public static double findFontSizeForWidth(Font font, String text, int maxWidth) {
        Font unitFont = new Font(font.getName(), 1);
        double unitSize = textWidth(unitFont, text);
        return maxWidth/unitSize;
    }

    public static double textHeight(Font font, String s) {
        Text text = new Text(s);
        text.setFont(font);
        return text.getBoundsInLocal().getHeight();
    }

    public static double textWidth(Font font, String s) {
        Text text = new Text(s);
        text.setFont(font);
        return text.getBoundsInLocal().getWidth();
    }
}
