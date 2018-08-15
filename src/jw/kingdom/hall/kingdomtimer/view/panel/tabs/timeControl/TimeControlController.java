package jw.kingdom.hall.kingdomtimer.view.panel.tabs.timeControl;

import com.google.common.io.Resources;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.domain.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.view.common.controller.TimeDisplayController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TimeControlController extends ControlledScreenImpl implements Initializable {

    @FXML
    Label lblTime;

    @FXML
    Button btnStart;

    @FXML
    Button btnPause;

    @FXML
    Button btnStop;

    @FXML
    TimeField tfFastTime;

    @FXML
    private TextField tfName;

    @FXML
    private TimeField atfTime;

    @FXML
    private CheckBox cbBuzzer;

    @FXML
    private TableView<MeetingTask> tvList;

    @FXML
    private TableColumn<MeetingTask, Button> tcDelete;

    @FXML
    private TableColumn<MeetingTask, Button> tcBuzzer;

    @FXML
    private TableColumn<MeetingTask, String> tcName;

    @FXML
    private TableColumn<MeetingTask, TimeField> tcTime;

    private TimeDisplayController timeDisplay;
    private TaskTableController tableController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadImage(btnStart, "icons/baseline_play_arrow_black_48dp.png");
        loadImage(btnPause, "icons/baseline_pause_black_48dp.png");
        loadImage(btnStop, "icons/baseline_stop_black_48dp.png");

        timeDisplay = new TimeDisplayController(lblTime);
        timeDisplay.setTime(0);
        getTimer().addController(timeDisplay);
        tableController = new TaskTableController(tvList,
                tcDelete,
                tcBuzzer,
                tcName,
                tcTime);
    }

    private void loadImage(Button button, String imgPath) {
        URL url = Resources.getResource(imgPath);
        try {
            BufferedImage bufferedImage = ImageIO.read(url);
            Image graphic = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView image = new ImageView(graphic);
            image.setPreserveRatio(true);
            image.setFitHeight(30);
            button.setGraphic(image);
            button.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoadTimeAction(ActionEvent event) {
        getTimer().startTime(tfFastTime.getAllSeconds());
    }

    @FXML
    private void onStartAction(ActionEvent event) {
        if(getTimer().isPause() && !getTimer().isStop()){
            getTimer().startTime(getTimer().getTime());
        } else {
            //TODO full implement in the future (is needs table to working)
        }
    }

    @FXML
    private void onPauseAction(ActionEvent event) {
        getTimer().setPause(true);
    }

    @FXML
    private void onStopAction(ActionEvent event) {
        getTimer().stop();
    }

    @Override
    protected Region getMainContainer() {
        return null;
    }

    private TimerCountdown getTimer() {
        return TimerCountdown.getInstance();
    }
}
