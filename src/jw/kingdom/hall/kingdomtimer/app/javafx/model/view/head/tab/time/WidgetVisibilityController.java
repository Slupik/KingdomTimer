package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.head.tab.time;

import javafx.scene.control.Button;

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
            //TODO repair
//            HandyWindow.getInstance().getStage().show();
        } else {
//            HandyWindow.getInstance().getStage().hide();
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
