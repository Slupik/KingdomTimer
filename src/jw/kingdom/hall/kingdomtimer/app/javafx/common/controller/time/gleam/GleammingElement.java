package jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.gleam;

import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

/**
 * All rights reserved & copyright ©
 */
public interface GleammingElement {
    void startGleamming();
    void applyColors(Background background, Paint textColor);
    Background getDefaultBackground();
    void endGleamming();
}
