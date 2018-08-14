package jw.kingdom.hall.kingdomtimer.view.viewer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import jw.kingdom.hall.kingdomtimer.model.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.view.common.controller.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.view.utils.view.ScreensController;

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

    private TimeDisplayController timeDisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        timeDisplay = new TimeDisplayController(tvTime);
        timeDisplay.setTime(0);
        timeDisplay.setColor(Color.BLACK);
        TimerCountdown.getInstance().addController(timeDisplay);

        mainContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            tvTime.setPadding(new Insets((int)(mainContainer.heightProperty().get()*0.2),0,0,0));

            Font font = tvTime.getFont();
            double newSize = findFontSizeThatCanFit(font, tvTime.getText(), (int) (mainContainer.widthProperty().get()*0.5));
            Font newFont = new Font(font.getName(), newSize);
            tvTime.setFont(newFont);
        });
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
}
