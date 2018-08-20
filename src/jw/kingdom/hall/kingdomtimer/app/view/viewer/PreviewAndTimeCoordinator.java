package jw.kingdom.hall.kingdomtimer.app.view.viewer;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * All rights reserved & copyright ©
 */
public class PreviewAndTimeCoordinator {
    private Pane mainContainer;
    private VBox timeContainer;
    private Label time;

    public PreviewAndTimeCoordinator(Pane mainContainer, VBox timeContainer, Label time) {
        this.mainContainer = mainContainer;
        this.timeContainer = timeContainer;
        this.time = time;

        init();
    }

    private void init() {

    }

    public void onMultimediaVisibilityChange() {

    }
}
