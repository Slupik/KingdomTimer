package jw.kingdom.hall.kingdomtimer.app.view.viewer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    ImageView imgMultimediaPreview;

    private TimeDisplayController timeDisplay;

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
        timeDisplay = new TimeDisplayController(tvTime);
        timeDisplay.setLightBackground(true);
        timeDisplay.setTime(0);
        TimerCountdown.getInstance().addController(timeDisplay);

        mainContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            tvTime.setPadding(new Insets((int)(mainContainer.heightProperty().get()*0.2),0,0,0));

            Font font = tvTime.getFont();
            double newSize = findFontSizeThatCanFit(font, tvTime.getText(), (int) (mainContainer.widthProperty().get()*0.5));
            Font newFont = new Font(font.getName(), newSize);
            tvTime.setFont(newFont);
        });
    }

    private void setupMultimediaPreview() {
        vbMultimediaPreview.minWidthProperty().bind(getMainContainer().widthProperty());
        vbMultimediaPreview.minHeightProperty().bind(getMainContainer().heightProperty());

        imgMultimediaPreview.fitWidthProperty().bind(getMainContainer().widthProperty().multiply(3).divide(7));
        imgMultimediaPreview.fitHeightProperty().bind(imgMultimediaPreview.fitWidthProperty().divide(9).multiply(16));

        getMultiPreviewer().addController(new MultimediaPreviewController(imgMultimediaPreview));
    }

    private static double findFontSizeThatCanFit(Font font, String s, int maxWidth) {
        double fontSize = font.getSize();
        double width = textWidth(font, s);
        if (maxWidth > width) {
            return fontSize * maxWidth / width;
        }
        return fontSize;
    }

    private static double textWidth(Font font, String s) {
        Text text = new Text(s);
        text.setFont(font);
        return text.getBoundsInLocal().getWidth();
    }

    private static MultimediaPreviewer getMultiPreviewer() {
        return MultimediaPreviewer.getInstance();
    }
}
