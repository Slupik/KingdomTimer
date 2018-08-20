package jw.kingdom.hall.kingdomtimer.app.view.viewer;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    private MultimediaPreviewController multimediaPreview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupMultimediaPreview();
        setupTimeView();
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
        vbTimer.minWidthProperty().bind(getMainContainer().widthProperty());
        vbTimer.minHeightProperty().bind(getMainContainer().heightProperty().add(tvTime.heightProperty().divide(7)));

        timeDisplay = new TimeDisplayController(tvTime);
        timeDisplay.setTime(0);
        TimerCountdown.getInstance().addController(timeDisplay);

        setupBackground();

        tvTime.setMinWidth(Region.USE_COMPUTED_SIZE);
        tvTime.setMaxWidth(Region.USE_COMPUTED_SIZE);
        mainContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            updateTimerText();
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
        multimediaPreview.addListener(()->{
            updateTimerText();
//            new Thread(()->{
//                try {
//                    Thread.sleep(30);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Platform.runLater(this::bindTimerContainerSize);
//            }).start();
            bindTimerContainerSize();
        });
    }

    private void updateTimerText() {
        if(multimediaPreview.isShowing()) {
            System.out.println("IS SHOWING");
            tvTime.setPadding(new Insets(0,(int)(mainContainer.widthProperty().get()*0.01),0,0));
            setupFontForTimerText(mainContainer.heightProperty().get()*0.32);
            System.out.println("tvTime.heightProperty().get() = " + tvTime.heightProperty().get());
            vbTimer.setAlignment(Pos.BOTTOM_RIGHT);
        } else {
            tvTime.setPadding(new Insets(0, 0,0,0));
            setupFontForTimerText(mainContainer.heightProperty().get());
            vbTimer.setAlignment(Pos.CENTER);
        }
    }

    private void setupFontForTimerText(double height) {
        int integerHeight = (int) height;
        System.out.println("integerHeight = " + integerHeight);
        Font font = tvTime.getFont();
        double newSize = findFontSizeThatCanFit(font, tvTime.getText(), integerHeight);
        System.out.println("newSize = " + newSize);
        Font newFont = new Font(font.getName(), newSize);
        tvTime.setFont(newFont);
    }

    private void setupBackground() {
        timeDisplay.setLightBackground(false);
        mainContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void bindTimerContainerSize() {
        if(multimediaPreview.isShowing()) {
            System.out.println("IF BIND");
            vbTimer.minWidthProperty().bind(getMainContainer().widthProperty());
//            vbTimer.minHeightProperty().bind(getMainContainer().heightProperty());
//            vbTimer.minHeightProperty().bind(getMainContainer().heightProperty().add(tvTime.heightProperty().divide(7)));
            vbTimer.minHeightProperty().bind(getMainContainer().heightProperty().add(new SimpleDoubleProperty(textHeight(tvTime.getFont(), tvTime.getText())).divide(7)));
            System.out.println("tvTime.heightProperty().getValue() = " + tvTime.heightProperty().getValue());
            System.out.println("calculated = " + textHeight(tvTime.getFont(), tvTime.getText()));
            System.out.println("tvTime.getFont().getSize() = " + tvTime.getFont().getSize());
        } else {
            System.out.println("ELSE BIND");
            vbTimer.minWidthProperty().bind(getMainContainer().widthProperty());
            vbTimer.minHeightProperty().bind(getMainContainer().heightProperty());
        }
    }

    private void setupMultimediaPreview() {
        vbMultimediaPreview.minWidthProperty().bind(getMainContainer().widthProperty());
        vbMultimediaPreview.minHeightProperty().bind(getMainContainer().heightProperty());

        imgMultimediaPreview.fitHeightProperty().bind(getMainContainer().heightProperty().divide(10).multiply(8));
        imgMultimediaPreview.fitWidthProperty().bind(imgMultimediaPreview.fitHeightProperty().multiply(16).divide(9));

        multimediaPreview = new MultimediaPreviewController(imgMultimediaPreview);
        getMultiPreviewer().addController(multimediaPreview);
    }

    private static double findFontSizeThatCanFit(Font font, String s, int maxHeight) {
        double fontSize = font.getSize();
        double height = textHeight(font, s);
        if (maxHeight > height) {
            return fontSize * maxHeight / height;
        } else {
            return fontSize * maxHeight / height;
        }
//        return fontSize;
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
