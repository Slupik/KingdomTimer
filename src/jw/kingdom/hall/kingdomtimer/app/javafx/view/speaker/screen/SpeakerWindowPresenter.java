package jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.screen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.MultimediaPreviewController;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpeakerWindowPresenter extends ControlledScreenBase implements PreviewAndTimeCoordinator.ElementsContainer, TimeInfoPresenter.Input {

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
    private PreviewAndTimeCoordinator layoutCoordinator;

    @Override
    protected void onSetup() {
        super.onSetup();
        new TimeInfoPresenter(this);

        setupMultimediaPreview();
        setupBackground();

        layoutCoordinator = new PreviewAndTimeCoordinator(this);
    }

    private void setupBackground() {
        mainContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setupMultimediaPreview() {
        multimediaPreview = new MultimediaPreviewController(imgMultimediaPreview);
        getMultiPreviewer().addController(multimediaPreview);

        multimediaPreview.addListener(()->
                layoutCoordinator.onMultimediaVisibilityChange(multimediaPreview.isShowing()));
        tvTime.widthProperty().addListener((observable, oldValue, newValue) ->
                Platform.runLater(()->
                        layoutCoordinator.onMultimediaVisibilityChange(multimediaPreview.isShowing())));
    }

    @Override
    public Region getMainContainer() {
        return mainContainer;
    }

    @Override
    public VBox getTimeContainer() {
        return vbTimer;
    }

    @Override
    public Label getTimeView() {
        return tvTime;
    }

    @Override
    public VBox getMultimediaContainer() {
        return vbMultimediaPreview;
    }

    @Override
    public ImageView getView() {
        return imgMultimediaPreview;
    }

    private static MultimediaPreviewer getMultiPreviewer() {
        return MultimediaPreviewer.getInstance();
    }

    @Override
    public MultimediaPreviewController getMultimediaPreviewController() {
        return multimediaPreview;
    }

    @Override
    public Label getTimeLbl() {
        return tvTime;
    }

    @Override
    public Region getBackgroundForTime() {
        return getMainContainer();
    }
}
