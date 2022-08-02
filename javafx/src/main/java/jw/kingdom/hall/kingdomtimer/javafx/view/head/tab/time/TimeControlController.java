package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.task.provider.TasksProviderCallbackProxy;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.BtnBuzzerController;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.display.TotalTimeControl;
import jw.kingdom.hall.kingdomtimer.javafx.common.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowType;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table.TaskTableController;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.timedirect.BtnTimeDirectForInstantController;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.timedirect.BtnTimeDirectForPanel;
import jw.kingdom.hall.kingdomtimer.javafx.view.widget.HandyWindow;

import java.util.List;
import java.util.Optional;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeControlController extends TabPresenter implements Initializable, StartPauseStopView.Listener,
        StartPauseStopView.Controller, FastPanelController.Input {

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTimeTotal;

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
    private HBox hbLoadScheduleContainer;

    @FXML
    private ProgressIndicator piLoadingTask;

    @FXML
    private TableView<TaskBean> tvList;

    @FXML
    private TableColumn<TaskBean, String> tcDelete;

    @FXML
    private TableColumn<TaskBean, String> tcBuzzer;

    @FXML
    private TableColumn<TaskBean, String> tcDirect;

    @FXML
    private TableColumn<TaskBean, String> tcName;

    @FXML
    private TableColumn<TaskBean, String> tcTime;

    @FXML
    private TableColumn<TaskBean, String> tcType;

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
                getSchedule(),
                getConfig(),
                tvList,
                tcDelete,
                tcBuzzer,
                tcDirect,
                tcName,
                tcTime,
                tcType
        );

        new TotalTimeControl(new TimeDisplayController(lblTimeTotal), getTimer(), getConfig());

        fastPanelController = new FastPanelController(this);

        getTimer().addListener(new TimeListenerProxy() {
            @Override
            public void onStart(TaskBean task) {
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

        setupScheduleLoadingIndicator();
    }

    private void setupScheduleLoadingIndicator() {
        piLoadingTask.setProgress(-1);//Constant waiting
        showLoadingIndicator(false);
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
        TaskBean task = new TaskBean();

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
        showLoadingIndicator(true);
        showScheduleDownloadButtons(false);
        getWindowData().getOnlineTasksProvider().getMeetingTasks(true, new TasksProviderCallbackProxy() {
            @Override
            public void onDownload(List<TaskBean> taskList) {
                getSchedule().setList(taskList);
                showLoadingIndicator(false);
                showScheduleDownloadButtons(true);
            }

            @Override
            public void onConnectionError() {
                showLoadingIndicator(false);
                showScheduleDownloadButtons(true);
                showConnectionErrorDialog(true);
            }
        });
    }

    @FXML
    private void loadTasksOnline(ActionEvent event) {
        showLoadingIndicator(true);
        showScheduleDownloadButtons(false);
        getWindowData().getOnlineTasksProvider().getMeetingTasks(false, new TasksProviderCallbackProxy() {
            @Override
            public void onDownload(List<TaskBean> taskList) {
                getSchedule().setList(taskList);
                showLoadingIndicator(false);
                showScheduleDownloadButtons(true);
            }

            @Override
            public void onConnectionError() {
                showLoadingIndicator(false);
                showScheduleDownloadButtons(true);
                showConnectionErrorDialog(false);
            }
        });
    }

    private void showConnectionErrorDialog(boolean circuit) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd!");
            alert.setHeaderText("Wykryto problemy z połączeniem.");
            alert.setContentText("Program nie może wczytać aktualnego programu zebrania. Problem może polegać na braku " +
                    "połączenia ze stroną wol.jw.org lub innego problemu technicznego. Możesz wczytać zapasowy program " +
                    "na ten dzień, ale pamiętaj go dostosować do realnego programu zebrania.\n" +
                    "Jeżeli strona wol.jw.org działa poprawnie w przeglądarce to zaistniały problem zgłoś KUSKowi.\n" +
                    "Zarówno wczytując zapasowy program jak i rezygnując z tej opcji system będzie działał stabilnie i " +
                    "dalej możesz korzystać z tego narzędzia. Po prostu będzie odrobinkę mniej wygodne :)");


            ButtonType btnApplyDefaultSchedule = new ButtonType("Wczytaj zapasowy program", ButtonBar.ButtonData.APPLY);
            ButtonType btnClose = new ButtonType("Zamknij", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(btnApplyDefaultSchedule, btnClose);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == btnApplyDefaultSchedule){
                getWindowData().getBackupTasksProvider().getMeetingTasks(circuit, new TasksProviderCallbackProxy() {
                    @Override
                    public void onDownload(List<TaskBean> taskList) {
                        getSchedule().setList(taskList);
                        showLoadingIndicator(false);
                        showScheduleDownloadButtons(true);
                    }

                    @Override
                    public void onConnectionError() {
                        System.err.println("Error during downloading default schedule. This should not happen.");
                    }
                });
            }
        });
    }

    private void showLoadingIndicator(boolean show) {
        piLoadingTask.setVisible(show);
        piLoadingTask.setManaged(show);
    }

    private void showScheduleDownloadButtons(boolean show) {
        hbLoadScheduleContainer.setVisible(show);
        hbLoadScheduleContainer.setManaged(show);
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
