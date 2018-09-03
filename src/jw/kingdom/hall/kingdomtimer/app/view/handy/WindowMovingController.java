package jw.kingdom.hall.kingdomtimer.app.view.handy;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class WindowMovingController {
    private final Stage stage;
    private final Group container;
    private double xOffset;
    private double yOffset;

    WindowMovingController(Stage stage, Group container) {
        this.stage = stage;
        this.container = container;
        init();
    }

    private void init() {
        container.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });
        container.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });
    }
}
