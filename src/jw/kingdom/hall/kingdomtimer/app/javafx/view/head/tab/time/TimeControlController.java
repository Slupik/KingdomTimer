package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.BtnBuzzerController;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.table.TaskTableController;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.timedirect.BtnTimeDirectForInstantController;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.timedirect.BtnTimeDirectForPanel;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.widget.HandyWindow;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeControlController extends TabPresenter implements Initializable, StartPauseStopView.Listener,
        StartPauseStopView.Controller, FastPanelController.Input {

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
    private Button btnWidgetVisibility;

    @FXML
    private CheckBox cbTimeToEvaluate;

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
    private TableColumn<MeetingTask, String> tcTime;

    @FXML
    private TableColumn<MeetingTask, String> tcType;

    private FastPanelController fastPanelController;
    private BtnTimeDirectForPanel timeDirectController;
    private BtnTimeDirectForPanel fastDirectController;
    private StartPauseStopView spsView;

    @Override
    public void onSetup() {
        super.onSetup();
        spsView = new StartPauseStopView();
        hbTimeControlsContainer.getChildren().add(spsView);
        spsView.addListener(this);
        spsView.setController(this);

        new BtnBuzzerController(getTimer(), btnBuzzer);

        timeDirectController = new BtnTimeDirectForPanel(btnCountdownDirect, getConfig());
        timeDirectController.setMedium(false);

        fastDirectController = new BtnTimeDirectForPanel(btnFastDirect, getConfig());
        fastDirectController.setMedium(false);

        BtnTimeDirectForInstantController instantDirectController = new BtnTimeDirectForInstantController(getTimer(), getConfig(), btnInstantDirect);
        instantDirectController.setMedium(true);

        TimeDisplayController timeDisplay = new TimeDisplayController(lblTime);
        getTimer().addDisplay(timeDisplay);
        new TaskTableController(
                getTimer(),
                getConfig(),
                tvList,
                tcDelete,
                tcBuzzer,
                tcDirect,
                tcName,
                tcTime,
                tcType
        );

        fastPanelController = new FastPanelController(this);

        getTimer().addListener(new TimeListenerProxy() {
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

        Platform.runLater(()-> new BackupPresenter().run());
        new WidgetVisibilityController((HandyWindow) getWindowsContainer().getAppWindow(WindowType.WIDGET), btnWidgetVisibility);
        setupTimeToEvaluate();
    }

    private void setupTimeToEvaluate() {
        cbTimeToEvaluate.setSelected(getConfig().getTimeToEvaluate()>=0);
        cbTimeToEvaluate.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                getConfig().setTimeToEvaluate(60);
            } else {
                getConfig().setTimeToEvaluate(-1);
            }
        });
    }

    @FXML
    private void handleAddTask(ActionEvent event) {
        MeetingTask task = new MeetingTask();

        task.setName(tfName.getText());
        tfName.clear();

        task.setTime(atfTime.getAllSeconds());
        atfTime.clear();

        task.setUseBuzzer(cbBuzzer.isSelected());
        cbBuzzer.setSelected(false);

        task.setCountdownDown(timeDirectController.isDirectDown());
        timeDirectController.reset();

        getTimer().addTask(task);
    }

    @FXML
    private void handleLoadTimeAction(ActionEvent event) {
        fastPanelController.handleLoadTimeAction();
    }

    @FXML
    private void loadOverseerTasksOnline(ActionEvent event) {
        getSchedule().setTasksOnline(getConfig(), true);
    }

    @FXML
    private void loadTasksOnline(ActionEvent event) {
        getSchedule().setTasksOnline(getConfig(), false);
    }

    @FXML
    private void handleAddTime(ActionEvent event) {
        fastPanelController.handleAddTime();
    }

    @FXML
    private void handleRemoveTime(ActionEvent event) {
        fastPanelController.handleRemoveTime();
    }

    @Override
    public void onStart() {
        getTimer().startNext();
    }

    @Override
    public void onPause() {
        getTimer().pause();
    }

    @Override
    public void onResume() {
        getTimer().resume();
    }

    @Override
    public void onStop() {
        getTimer().stop();
    }

    @Override
    public boolean isToExecuteSPSAction(StartPauseStopView.ActionType type) {
        if(type==StartPauseStopView.ActionType.START) {
            return getTimer().getList().size()!=0;
        }
        return true;
    }

    @Override
    public TimeField getFastTimeField() {
        return tfFastTime;
    }

    @Override
    public BtnTimeDirectForPanel getFastDirectController() {
        return fastDirectController;
    }
}
