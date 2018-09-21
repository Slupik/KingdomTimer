package jw.kingdom.hall.kingdomtimer.app.javafx.common.zoom;

import javafx.scene.layout.Region;

/**
 * All rights reserved & copyright ©
 */
public class ZoomController {
    private final Region container;

    public ZoomController (Region container) {
        this.container = container;
        init();
    }

    private void init() {
        AnimatedZoomOperator zoomOperator = new AnimatedZoomOperator();
        getContainer().setOnScroll(event -> {
            double zoomFactor = 1.1;
            if (event.getDeltaY() <= 0) {
                // zoom out
                zoomFactor = 1 / zoomFactor;
            }
            zoomOperator.zoom(getContainer(), zoomFactor);
        });
    }

    private Region getContainer(){
        return container;
    }
}
