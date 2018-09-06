package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.app.view.handy.HandyWindow;

/**
 * All rights reserved & copyright ©
 */
class WidgetVisibilityController {
    private final Button button;
    private boolean isWidgetVisible = true;

    WidgetVisibilityController(Button btnVisibilityChanger) {
        this.button = btnVisibilityChanger;
        init();
    }

    private void init() {
        setupName();
        button.setOnAction(event -> {
            isWidgetVisible = !isWidgetVisible;
            setupName();
            setupVisibility();
        });
    }

    private void setupVisibility() {
        if(isWidgetVisible) {
            HandyWindow.getInstance().getStage().show();
        } else {
            HandyWindow.getInstance().getStage().hide();
        }
    }

    private void setupName() {
        if(isWidgetVisible) {
            button.setText("Ukryj widget");
        } else {
            button.setText("Pokaż widget");
        }
    }
}
