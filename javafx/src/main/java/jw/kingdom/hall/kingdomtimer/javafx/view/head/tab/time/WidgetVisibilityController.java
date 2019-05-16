package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.javafx.view.widget.HandyWindow;

/**
 * All rights reserved & copyright ©
 */
class WidgetVisibilityController {
    private final HandyWindow window;
    private final Button button;
    private boolean isWidgetVisible = true;

    WidgetVisibilityController(HandyWindow window, Button btnVisibilityChanger) {
        this.window = window;
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
            window.getStage().show();
        } else {
            window.getStage().hide();
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
