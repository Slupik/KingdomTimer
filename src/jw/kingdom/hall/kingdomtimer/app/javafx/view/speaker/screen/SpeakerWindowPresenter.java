package jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.MultimediaPreviewController;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen.coordinator.SpeakerLayoutCoordinator;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpeakerWindowPresenter extends ControlledScreenBase implements TimeInfoPresenter.Input, SpeakerLayoutCoordinator.Input {

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

    private MultimediaPreviewController multimediaPreview;

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
        multimediaPreview = new MultimediaPreviewController(imgMultimediaPreview);
        getMultiPreviewer().addController(multimediaPreview);
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

    private static MultimediaPreviewer getMultiPreviewer() {
        return MultimediaPreviewer.getInstance();
    }

    @Override
    public ImageView getPreviewView() {
        return imgMultimediaPreview;
    }

    @Override
    public MultimediaPreviewController getMultimediaPreviewController() {
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
