package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.timedirect.BtnTimeDirectBase;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.timedirect.BtnTimeDirectForInstantController;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.timedirect.BtnTimeDirectForPanel;
import jw.kingdom.hall.kingdomtimer.data.PredefinedTaskList;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static jw.kingdom.hall.kingdomtimer.app.view.utils.ButtonUtils.loadMediumImage;

/**
 * All rights reserved & copyright ©
 */
public class TimeControlController extends ControlledScreenImpl implements Initializable {

    @FXML
    private Label lblTime;

    @FXML
    private Button btnFastDirect;

    @FXML
    private Button btnInstantDirect;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnStop;

    @FXML
    private Button btnBuzzer;

    @FXML
    private TimeField tfFastTime;

    @FXML
    private TextField tfName;

    @FXML
    private TimeField atfTime;

    @FXML
    private CheckBox cbBuzzer;

    @FXML
    private Button btnCountdownDirect;

    @FXML
    private TableView<MeetingTask> tvList;

    @FXML
    private TableColumn<MeetingTask, String> tcDelete;

    @FXML
    private TableColumn<MeetingTask, String> tcBuzzer;

    @FXML
    private TableColumn<MeetingTask, String> tcDirect;

    @FXML
    private TableColumn<MeetingTask, String> tcName;

    @FXML
    private TableColumn<MeetingTask, TimeField> tcTime;

    private TimeDisplayController timeDisplay;
    private TaskTableController tableController;
    private BtnBuzzerController buzzerController;
    private BtnTimeDirectForPanel timeDirectController;
    private BtnTimeDirectForInstantController instantDirectController;
    private BtnTimeDirectForPanel fastDirectController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMediumImage(btnStart, "icons/baseline_play_arrow_black_48dp.png");
        loadMediumImage(btnPause, "icons/baseline_pause_black_48dp.png");
        loadMediumImage(btnStop, "icons/baseline_stop_black_48dp.png");
        buzzerController = new BtnBuzzerController(btnBuzzer);

        timeDirectController = new BtnTimeDirectForPanel(btnCountdownDirect);
        timeDirectController.setMedium(false);

        fastDirectController = new BtnTimeDirectForPanel(btnFastDirect);
        fastDirectController.setMedium(false);

        instantDirectController = new BtnTimeDirectForInstantController(btnInstantDirect);
        instantDirectController.setMedium(true);

        timeDisplay = new TimeDisplayController(lblTime);
        timeDisplay.setTime(0);
        getTimer().addController(timeDisplay);
        tableController = new TaskTableController(tvList,
                tcDelete,
                tcBuzzer,
                tcDirect,
                tcName,
                tcTime);

        setupInstantDirectController();
    }

    private void setupInstantDirectController() {
        instantDirectController.addListener(new BtnTimeDirectBase.ListenerImpl() {
            @Override
            public void onDirectChange(boolean isDirectDown) {
//                TimerCountdown.getInstance().setDirectTimeDown(isDirectDown);
            }
        });
    }

    private boolean isWeekend() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return (cl.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                cl.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
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

        task.setCountdownDown(timeDirectController.isDirectDown());
        timeDirectController.reset();

        tableController.getList().add(task);
    }

    @FXML
    private void handleLoadTimeAction(ActionEvent event) {
        MeetingTask task = new MeetingTask();
        task.setTimeInSeconds(tfFastTime.getAllSeconds());
        tfFastTime.setSeconds(0);
        task.setCountdownDown(fastDirectController.isDirectDown());
        fastDirectController.reset();
        getTimer().start(task);
    }

    @FXML
    private void loadTasksOnline(ActionEvent event) {
        new Thread(() -> {
            List<MeetingTask> tasks;
            if(isWeekend()) {
                tasks = PredefinedTaskList.getWeekendTasks();
            } else {
                tasks = PredefinedTaskList.getWeekTasks();
            }
            tableController.getList().clear();
            tableController.getList().addAll(tasks);
        }).start();
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
