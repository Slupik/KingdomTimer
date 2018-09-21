package jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface TimeInfoContainer {
    Region getMainContainer();
    VBox getTimeContainer();
    Label getTimeView();
}
