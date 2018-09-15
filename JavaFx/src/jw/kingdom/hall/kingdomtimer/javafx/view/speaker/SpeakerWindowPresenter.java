package jw.kingdom.hall.kingdomtimer.javafx.view.speaker;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.javafx.control.gleam.GleamControllerImpl;
import jw.kingdom.hall.kingdomtimer.javafx.control.preview.MultimediaPreviewController;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.screen.ControlledScreenBase;
import jw.kingdom.hall.kingdomtimer.javafx.temp.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.usecase.time.display.TimeDisplayProxy;

/**
 * All rights reserved & copyright ©
 */
public class SpeakerWindowPresenter extends ControlledScreenBase implements PreviewAndTimeCoordinator.ElementsContainer {

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
    private GleamControllerImpl gleammer;
    private MultimediaPreviewController multimediaPreview;
    private PreviewAndTimeCoordinator layoutCoordinator;

    @Override
    protected void onStart() {
        super.onStart();
        setupMultimediaPreview();
        setupTimeView();
        setupGleam();
    }

    private void setupGleam() {
        gleammer = new GleamControllerImpl(mainContainer, timeDisplay, getWindowData().getGleamSwitcher());
        getWindowData().getCountdown().addDisplay(new TimeDisplayProxy() {
            @Override
            public void onTimeOut() {
                if(getWindowData().getConfig().isEnabledGleaming()) {
                    gleammer.play();
                }
            }
        });
    }

    private void setupTimeView() {
        timeDisplay = new TimeDisplayController(tvTime);
        getWindowData().getCountdown().addDisplay(timeDisplay);

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
        multimediaPreview = new MultimediaPreviewController(imgMultimediaPreview, getWindowData().getConfig());
        getMultiPreviewer().addController(multimediaPreview);
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
