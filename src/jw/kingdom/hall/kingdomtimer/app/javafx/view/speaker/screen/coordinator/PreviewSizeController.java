package jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen.coordinator;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * All rights reserved & copyright ©
 */
public class PreviewSizeController {

    private static double PREVIEW_RATIO = 16d/9d;
    private static double HEIGHT_RATIO = 4d/5d;

    private final Region containerWithDisplay;
    private final ImageView previewView;

    public PreviewSizeController(Region containerWithDisplay, ImageView previewView) {
        this.containerWithDisplay = containerWithDisplay;
        this.previewView = previewView;
        init();
    }

    private void init() {
        containerWithDisplay.widthProperty().addListener(
                (observable, oldValue, newValue) -> Platform.runLater(this::adjustSize)
        );
        containerWithDisplay.heightProperty().addListener(
                (observable, oldValue, newValue) -> Platform.runLater(this::adjustSize)
        );
    }

    private void adjustSize() {
        double containerHeight = containerWithDisplay.heightProperty().doubleValue();
        double containerWidth = containerWithDisplay.widthProperty().doubleValue();//-50;

        double heightPropose = containerHeight*HEIGHT_RATIO;
        double widthPropose = heightPropose* PREVIEW_RATIO;

        double viewSize = heightPropose;

        if(widthPropose > containerWidth) {
            previewView.setFitWidth(containerWidth);
            viewSize = containerWidth/ PREVIEW_RATIO;
            previewView.setFitHeight(viewSize);
        } else {
            previewView.setFitWidth(widthPropose);
            previewView.setFitHeight(viewSize);
        }
    }
}
