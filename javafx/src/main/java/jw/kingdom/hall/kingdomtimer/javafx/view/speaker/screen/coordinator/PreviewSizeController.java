package jw.kingdom.hall.kingdomtimer.javafx.view.speaker.screen.coordinator;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.MultimediaDisplayImpl;

/**
 * All rights reserved & copyright ©
 */
class PreviewSizeController {

    private static double PREVIEW_RATIO = 16d/9d;
    private static double HEIGHT_RATIO = 4d/5d;

    private final Input input;

    PreviewSizeController(Input input) {
        this.input = input;
        init();
    }

    private void init() {
        input.getMultimediaContainer().minWidthProperty().bind(input.getMainContainer().widthProperty());
        input.getMultimediaContainer().minHeightProperty().bind(input.getMainContainer().heightProperty());
    }

    void adjustSize(double containerHeight, double containerWidth) {
        adjustSize(containerHeight, containerWidth, () -> {});
    }

    void adjustSize(double containerHeight, double containerWidth, Runnable callback) {
        double heightPropose = containerHeight*HEIGHT_RATIO;
        double widthPropose = heightPropose* PREVIEW_RATIO;

        double viewSize = heightPropose;

        if(widthPropose > containerWidth) {
            getPreviewView().setFitWidth(containerWidth);
            viewSize = containerWidth/PREVIEW_RATIO;
            getPreviewView().setFitHeight(viewSize);
        } else {
            getPreviewView().setFitWidth(widthPropose);
            getPreviewView().setFitHeight(viewSize);
        }
        callback.run();
    }

    private ImageView getPreviewView() {
        return input.getPreviewView();
    }

    interface Input {
        MultimediaDisplayImpl getMultimediaPreviewController();
        ImageView getPreviewView();
        VBox getMultimediaContainer();
        Region getMainContainer();
    }
}
