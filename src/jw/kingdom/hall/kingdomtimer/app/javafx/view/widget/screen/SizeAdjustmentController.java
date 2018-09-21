package jw.kingdom.hall.kingdomtimer.app.javafx.view.widget.screen;

import javafx.application.Platform;
import javafx.scene.control.Label;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.AppWindow;

/**
 * All rights reserved & copyright ©
 */
class SizeAdjustmentController {

    private final Input input;
    private double lastSize = -1;

    SizeAdjustmentController(Input input) {
        this.input = input;
        init();
    }

    //It's must be delayed because of JavaFx limitation/bug
    private void init() {
        Platform.runLater(()->{
            lastSize = getLblWithTime().widthProperty().doubleValue();
            getLblWithTime().widthProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(()->{
                double diff = newValue.doubleValue()-lastSize;
                lastSize = newValue.doubleValue();
                double stageWidth = getParentWindow().getStage().getWidth()+diff;
                getParentWindow().getStage().setWidth(stageWidth);
            }));
        });
    }

    private AppWindow getParentWindow() {
        return input.getParentWindow();
    }

    private Label getLblWithTime() {
        return input.getLblWithTime();
    }

    interface Input {
        AppWindow getParentWindow();
        Label getLblWithTime();
    }
}
