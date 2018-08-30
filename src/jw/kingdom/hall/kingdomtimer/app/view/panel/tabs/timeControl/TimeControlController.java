package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.view.common.custom.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.timedirect.BtnTimeDirectBase;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.timedirect.BtnTimeDirectForInstantController;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.timedirect.BtnTimeDirectForPanel;
import jw.kingdom.hall.kingdomtimer.data.PredefinedTaskList;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.domain.schedule.NotEnoughTasksException;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TimeControlController extends ControlledScreenImpl implements Initializable, StartPauseStopView.Listener,
StartPauseStopView.Controller {

    @FXML
    private Label lblTime;

    @FXML
    private Button btnFastDirect;

    @FXML
    private Button btnInstantDirect;

    @FXML
    private HBox hbTimeControlsContainer;

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

    @FXML
    private TableColumn<MeetingTask, String> tcType;

    private TimeDisplayController timeDisplay;
    private TaskTableController tableController;
    private BtnBuzzerController buzzerController;
    private BtnTimeDirectForPanel timeDirectController;
    private BtnTimeDirectForInstantController instantDirectController;
    private BtnTimeDirectForPanel fastDirectController;
    private StartPauseStopView spsView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spsView = new StartPauseStopView();
        hbTimeControlsContainer.getChildren().add(spsView);
        spsView.addListener(this);
        spsView.setController(this);

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
                tcTime,
                tcType
        );

        setupInstantDirectController();
        TimerCountdown.getInstance().addListener(new TimerCountdownListener() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                spsView.setupForStart();
            }

            @Override
            public void onPause() {
                super.onPause();
                spsView.setupForPause();
            }

            @Override
            public void onResume() {
                super.onPause();
                spsView.setupForUnPause();
            }

            @Override
            public void onStop() {
                super.onStop();
                spsView.setupForStop();
            }
        });
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

        MeetingSchedule.getInstance().addTask(task);
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
            MeetingSchedule.getInstance().clear();
            MeetingSchedule.getInstance().addTask(tasks);
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

    @Override
    protected Region getMainContainer() {
        return null;
    }

    private TimerCountdown getTimer() {
        return TimerCountdown.getInstance();
    }

    @Override
    public void onStart() {
        try {
            MeetingTask task = MeetingSchedule.getInstance().bringOutFirstTask();
            getTimer().start(task);
        } catch (NotEnoughTasksException ignore) {}
    }

    @Override
    public void onPause() {
        getTimer().pause();
    }

    @Override
    public void onUnPause() {
        getTimer().resume();
    }

    @Override
    public void onStop() {
        getTimer().stop();
    }

    @Override
    public boolean isToExecuteSPSAction(StartPauseStopView.ActionType type) {
        if(type==StartPauseStopView.ActionType.START) {
            return MeetingSchedule.getInstance().getList().size()!=0;
        }
        return true;
    }
}
