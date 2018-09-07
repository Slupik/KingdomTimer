package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.container.WindowsContainer;

/**
 * All rights reserved & copyright ©
 */
class WidgetVisibilityController {
    private final Button button;
    private final WindowsContainer windowsContainer;
    private boolean isWidgetVisible = true;

    WidgetVisibilityController(Button btnVisibilityChanger, WindowsContainer windowsContainer) {
        this.button = btnVisibilityChanger;
        this.windowsContainer = windowsContainer;
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
            windowsContainer.getAppWindow(WindowType.WIDGET).getStage().show();
        } else {
            windowsContainer.getAppWindow(WindowType.WIDGET).getStage().hide();
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
