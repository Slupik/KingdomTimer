package jw.kingdom.hall.kingdomtimer.javafx.control.gleam;

import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.usecase.time.display.TimeDisplay;

/**
 * All rights reserved & copyright ©
 */
public interface GleammingTimeDisplay<T> extends TimeDisplay<T> {
    void setTextColor(Paint fill);
    void setLightBackground(boolean lightBackground);
    void resetColorToLast();
}
