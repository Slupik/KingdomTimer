package jw.kingdom.hall.kingdomtimer.javafx.view.handy;

import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class MobilityController {
    private final Stage stage;
    private final Group container;
    private double xOffset;
    private double yOffset;

    MobilityController(Stage stage, Group container) {
        this.stage = stage;
        this.container = container;
        init();
    }

    private void init() {
        container.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        container.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });
    }
}
