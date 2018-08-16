package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;

import java.net.URL;
import java.util.ResourceBundle;

import static jw.kingdom.hall.kingdomtimer.app.view.utils.ButtonUtils.loadImage;

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
    Button btnBuzzer;

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
    private TableColumn<MeetingTask, String> tcDelete;

    @FXML
    private TableColumn<MeetingTask, String> tcBuzzer;

    @FXML
    private TableColumn<MeetingTask, String> tcName;

    @FXML
    private TableColumn<MeetingTask, TimeField> tcTime;

    private TimeDisplayController timeDisplay;
    private TaskTableController tableController;
    private BtnBuzzerController buzzerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadImage(btnStart, "icons/baseline_play_arrow_black_48dp.png");
        loadImage(btnPause, "icons/baseline_pause_black_48dp.png");
        loadImage(btnStop, "icons/baseline_stop_black_48dp.png");
        buzzerController = new BtnBuzzerController(btnBuzzer);

        timeDisplay = new TimeDisplayController(lblTime);
        timeDisplay.setTime(0);
        getTimer().addController(timeDisplay);
        tableController = new TaskTableController(tvList,
                tcDelete,
                tcBuzzer,
                tcName,
                tcTime);
    }

    @FXML
    private void handleAddTask(ActionEvent event) {
        MeetingTask task = new MeetingTask();

        task.setName(tfName.getText());
        tfName.clear();

        task.setTimeInSeconds(atfTime.getAllSeconds());
        atfTime.clear();

        task.setUseBuzzer(cbBuzzer.isSelected());
        cbBuzzer.setSelected(false);

        tableController.getList().add(task);
    }

    @FXML
    private void handleLoadTimeAction(ActionEvent event) {
        MeetingTask task = new MeetingTask();
        task.setTimeInSeconds(tfFastTime.getAllSeconds());
        getTimer().start(task);
    }

    @FXML
    private void handleAddTime(ActionEvent event) {
        getTimer().addTime(tfFastTime.getAllSeconds());
    }

    @FXML
    private void handleRemoveTime(ActionEvent event) {
        getTimer().removeTime(tfFastTime.getAllSeconds());
    }

    @FXML
    private void onStartAction(ActionEvent event) {
        if(getTimer().isPause() && !getTimer().isStop()){
            getTimer().resume();
        } else {
            MeetingTask task = tvList.getItems().get(0);
            getTimer().start(task);
            tableController.getList().remove(task);
            buzzerController.loadTask(task);
        }
    }

    @FXML
    private void onPauseAction(ActionEvent event) {
        getTimer().pause();
    }

    @FXML
    private void onStopAction(ActionEvent event) {
        getTimer().stop();
        buzzerController.loadTask(null);
    }

    @Override
    protected Region getMainContainer() {
        return null;
    }

    private TimerCountdown getTimer() {
        return TimerCountdown.getInstance();
    }
}
