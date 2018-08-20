package jw.kingdom.hall.kingdomtimer.app.view.viewer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
 * All rights reserved & copyright ©
 */
public class ViewerController extends ControlledScreenImpl implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTimeView();
        setupMultimediaPreview();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        super.setScreenParent(screenPage);
    }

    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }

    private void setupTimeView() {
        bindTimerContainerSize();
        timeDisplay = new TimeDisplayController(tvTime);
        timeDisplay.setTime(0);
        TimerCountdown.getInstance().addController(timeDisplay);

        setupBackground();

        tvTime.setMinWidth(Region.USE_COMPUTED_SIZE);
        tvTime.setMaxWidth(Region.USE_COMPUTED_SIZE);
        mainContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            tvTime.setPadding(new Insets(0,(int)(mainContainer.widthProperty().get()*0.01),0,0));

            Font font = tvTime.getFont();
            double newSize = findFontSizeThatCanFit(font, tvTime.getText(), (int) (mainContainer.heightProperty().get()*0.32));
            Font newFont = new Font(font.getName(), newSize);
            tvTime.setFont(newFont);
        });

        gleammer = new GleamController(mainContainer);
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

    private void setupBackground() {
        timeDisplay.setLightBackground(false);
        mainContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void bindTimerContainerSize() {
        vbTimer.minWidthProperty().bind(getMainContainer().widthProperty());
        vbTimer.minHeightProperty().bind(getMainContainer().heightProperty().add(tvTime.heightProperty().divide(7)));
    }

    private void setupMultimediaPreview() {
        vbMultimediaPreview.minWidthProperty().bind(getMainContainer().widthProperty());
        vbMultimediaPreview.minHeightProperty().bind(getMainContainer().heightProperty());

        imgMultimediaPreview.fitHeightProperty().bind(getMainContainer().heightProperty().divide(10).multiply(8));
        imgMultimediaPreview.fitWidthProperty().bind(imgMultimediaPreview.fitHeightProperty().multiply(16).divide(9));

        getMultiPreviewer().addController(new MultimediaPreviewController(imgMultimediaPreview));
    }

    //TODO after multimedia screen preview off timer should be bigger
    private static double findFontSizeThatCanFit(Font font, String s, int maxHeight) {
        double fontSize = font.getSize();
        double height = textHeight(font, s);
        if (maxHeight > height) {
            return fontSize * maxHeight / height;
        }
        return fontSize;
    }

    private static double textHeight(Font font, String s) {
        Text text = new Text(s);
        text.setFont(font);
        return text.getBoundsInLocal().getHeight();
    }

    private static MultimediaPreviewer getMultiPreviewer() {
        return MultimediaPreviewer.getInstance();
    }
}
