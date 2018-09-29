package jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen.coordinator;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.MultimediaDisplayImpl;

import static jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen.coordinator.LabelVersion.SMALL;
import static jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen.coordinator.LabelVersion.UNKNOWN;

/**
 * All rights reserved & copyright ©
 */
public class SpeakerLayoutCoordinator implements LabelContainerSizeController.Input, LabelSizeController.Input, PreviewSizeController.Input {

    private final Input input;
    private final LabelContainerSizeController labelContainerSizeController;
    private final LabelSizeController labelSizeController;
    private final PreviewSizeController previewSizeController;

    public SpeakerLayoutCoordinator(Input input) {
        this.input = input;
        init();
        labelContainerSizeController = new LabelContainerSizeController(this);
        labelSizeController = new LabelSizeController(this);
        previewSizeController = new PreviewSizeController(this);
    }

    private void init() {
        getMainContainer().widthProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(this::adjustSizeToNewConditions));
        getMainContainer().heightProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(this::adjustSizeToNewConditions));
        input.getMultimediaPreviewController().addListener(isShowing -> Platform.runLater(this::adjustSizeToNewConditions));
    }

    private void adjustSizeToNewConditions() {
        adjustPreview(this::adjustTimeInfo);
    }

    private void adjustPreview(Runnable callback) {
        previewSizeController.adjustSize(
                getMainContainer().heightProperty().get(),
                getMainContainer().widthProperty().get(),
                callback
        );
    }

    private void adjustTimeInfo() {
        short sizeVersion = getSizeVersion();
        double freeHeight;
        if(sizeVersion==SMALL || sizeVersion==UNKNOWN) {
            freeHeight = getMainContainer().heightProperty().get() - input.getPreviewView().fitHeightProperty().get();
        } else {
            freeHeight = getMainContainer().heightProperty().get();
        }

        labelSizeController.adjustSize(
                sizeVersion,
                freeHeight,
                getMainContainer().widthProperty().get(),
                () -> labelContainerSizeController.adjustSize(sizeVersion)
        );
    }

    private short getSizeVersion() {
        if(input.getMultimediaPreviewController().isShowing()) {
            return SMALL;
        } else {
            return LabelVersion.BIG;
        }
    }

    @Override
    public VBox getTimeLabelContainer() {
        return input.getTimeLabelContainer();
    }

    @Override
    public Region getMainContainer() {
        return input.getMainContainer();
    }

    @Override
    public Label getTimeLabel() {
        return input.getTimeLabel();
    }

    @Override
    public MultimediaDisplayImpl getMultimediaPreviewController() {
        return input.getMultimediaPreviewController();
    }

    @Override
    public ImageView getPreviewView() {
        return input.getPreviewView();
    }

    @Override
    public VBox getMultimediaContainer() {
        return input.getMultimediaContainer();
    }

    public interface Input extends LabelContainerSizeController.Input, LabelSizeController.Input, PreviewSizeController.Input {}
}
