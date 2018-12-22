package jw.kingdom.hall.kingdomtimer.javafx.view.speaker.screen;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.MultimediaDisplayImpl;
import jw.kingdom.hall.kingdomtimer.javafx.domain.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.view.speaker.screen.coordinator.SpeakerLayoutCoordinator;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpeakerViewPresenter extends ControlledScreenBase implements TimeInfoPresenter.Input, SpeakerLayoutCoordinator.Input {

    @FXML
    AnchorPane mainContainer;

    @FXML
    Label tvTime;

    @FXML
    VBox vbMultimediaPreview;

    @FXML
    VBox vbTimer;

    @FXML
    ImageView imgMultimediaPreview;

    private MultimediaDisplayImpl multimediaPreview;

    @Override
    protected void onSetup() {
        super.onSetup();
        setupMultimediaPreview();
        setupBackground();

        new TimeInfoPresenter(this);
        new SpeakerLayoutCoordinator(this);
    }

    private void setupBackground() {
        mainContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setupMultimediaPreview() {
        multimediaPreview = new MultimediaDisplayImpl(imgMultimediaPreview);
        getWindowData().getSpeakerPreviewController().addDisplay(multimediaPreview);
        multimediaPreview.setShowing(getConfig().isEnabledShowMultimedia());

        handleStageVisibilityChange(getWindowsContainer().getAppWindow(WindowType.SPEAKER).getStage().isShowing());
        getWindowsContainer().getAppWindow(WindowType.SPEAKER).getStage().showingProperty().addListener(
                (observable, oldValue, newValue) -> handleStageVisibilityChange(newValue)
        );
    }

    private void handleStageVisibilityChange(boolean isShowing) {
        if(isShowing) {
            multimediaPreview.setShowing(getConfig().isEnabledShowMultimedia());
        } else {
            multimediaPreview.setShowing(false);
        }
    }

    @Override
    public VBox getTimeLabelContainer() {
        return vbTimer;
    }

    @Override
    public Region getMainContainer() {
        return mainContainer;
    }

    @Override
    public Label getTimeLabel() {
        return tvTime;
    }

    @Override
    public VBox getMultimediaContainer() {
        return vbMultimediaPreview;
    }

    @Override
    public ImageView getPreviewView() {
        return imgMultimediaPreview;
    }

    @Override
    public MultimediaDisplayImpl getMultimediaPreviewController() {
        return multimediaPreview;
    }

    @Override
    public Label getTimeView() {
        return tvTime;
    }

    @Override
    public Region getBackgroundForTime() {
        return getMainContainer();
    }
}
