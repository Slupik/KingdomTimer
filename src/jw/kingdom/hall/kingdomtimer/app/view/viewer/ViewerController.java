package jw.kingdom.hall.kingdomtimer.app.view.viewer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.countdown.gleam.GlobalGleamController;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.MultimediaPreviewController;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.view.loader.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class ViewerController extends ControlledScreenImpl implements Initializable, PreviewAndTimeCoordinator.ElementsContainer {

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

    private TimeDisplayController timeDisplay;
    private GleamController gleammer;
    private MultimediaPreviewController multimediaPreview;
    private PreviewAndTimeCoordinator layoutCoordinator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupMultimediaPreview();
        setupTimeView();
        setupGleam();
    }

    private void setupGleam() {
        gleammer = new GleamController(mainContainer, timeDisplay);
        TimerCountdown.getInstance().addListener(new TimerCountdownListener() {
            @Override
            public void onTimeOut() {
                super.onTimeOut();
                if(GlobalGleamController.getInstance().isEnable()) {
                    gleammer.play();
                }
            }
        });
    }

    private void setupTimeView() {
        timeDisplay = new TimeDisplayController(tvTime);
        timeDisplay.setTime(0);
        TimerCountdown.getInstance().addController(timeDisplay);

        setupBackground();

        layoutCoordinator = new PreviewAndTimeCoordinator(this);

        timeDisplay.addListener(() -> layoutCoordinator.onMultimediaVisibilityChange(multimediaPreview.isShowing()));
        multimediaPreview.addListener(()-> layoutCoordinator.onMultimediaVisibilityChange(multimediaPreview.isShowing()));
    }

    private void setupBackground() {
        timeDisplay.setLightBackground(false);
        mainContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setupMultimediaPreview() {
        multimediaPreview = new MultimediaPreviewController(imgMultimediaPreview);
        getMultiPreviewer().addController(multimediaPreview);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        super.setScreenParent(screenPage);
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
}
