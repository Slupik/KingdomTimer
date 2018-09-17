package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.record;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.DefaultVoiceRecorderRecordControlListener;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingScheduleListener;
import jw.kingdom.hall.kingdomtimer.recorder.RecordListenerProxy;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class RecordController extends TabPresenter implements StartPauseStopView.Listener {

    @FXML
    private VBox vbMainContainer;

    @FXML
    private Label lblTime;

    @FXML
    private HBox hbControlsContainer;

    @FXML
    private CheckBox cbAutopilot;

    @FXML
    private CheckBox cbAutoSeparate;

    @FXML
    private TextField tfPath;

    private TimeDisplayController controller;
    private StartPauseStopView spsView;

    @Override
    public void onSetup() {
        addRecordListener();

        spsView = new StartPauseStopView();
        hbControlsContainer.getChildren().add(spsView);
        spsView.addListener(this);
        getRecorder().addListener(new DefaultVoiceRecorderRecordControlListener() {
            @Override
            public void onStart() {
                super.onStart();
                spsView.setupForStart();
            }

            @Override
            public void onPause() {
                super.onPause();
                spsView.setupForPause();
            }

            @Override
            public void onResume() {
                super.onResume();
                spsView.setupForUnPause();
            }

            @Override
            public void onStop() {
                super.onStop();
                spsView.setupForStop();
            }
        });

        controller = new TimeDisplayController(lblTime);
        controller.setTime(0);

        getSchedule().addListener(new MeetingScheduleListener() {
            private MeetingTask.Type lastType = null;

            @Override
            public void onMeetingStart() {
                super.onMeetingStart();
                lastType = null;
                if (isAutopilotOn()) {
                    spsView.start();
                }
            }

            @Override
            public void onNextTask(int index, MeetingTask task) {
                super.onNextTask(index, task);
                if (task != null && (!task.getType().equals(lastType) && lastType != null) && isAutoSeparateOn()) {
                    getRecorder().stop();
                    getRecorder().start();
                }
                if (task == null) {
                    lastType = null;
                } else {
                    lastType = task.getType();
                }
            }

            @Override
            public void onMeetingEnd() {
                super.onMeetingEnd();
                if (isAutopilotOn()) {
                    if (Platform.isFxApplicationThread()) {
                        spsView.stop();
                    } else {
                        Platform.runLater(() -> spsView.stop());
                    }
                }
            }
        });

        loadConfig();
        bindConfig();
    }

    private void bindConfig() {
        cbAutopilot.selectedProperty().addListener((observable, oldValue, newValue) -> {
            getConfig().setEnabledAutopilot(newValue);
            cbAutoSeparate.setDisable(!newValue);
        });
        cbAutoSeparate.selectedProperty().addListener((observable, oldValue, newValue) ->
                getConfig().setEnabledAutoSeparate(newValue));
    }

    private void loadConfig() {
        cbAutopilot.setSelected(getConfig().isEnabledAutopilot());
        cbAutoSeparate.setSelected(getConfig().isAutoSeparate());
        tfPath.setText(getConfig().getRecordDestPath());
    }

    private void addRecordListener() {
        getRecorder().addListener(new RecordListenerProxy() {
            @Override
            public void onNewTime(int seconds) {
                controller.setTime(seconds);
            }
        });
    }

    private boolean isAutoSeparateOn() {
        return isAutopilotOn() && cbAutoSeparate.isSelected();
    }

    private boolean isAutopilotOn() {
        return cbAutopilot.isSelected();
    }

    @Override
    public void onStart() {
        getRecorder().start();
    }

    @Override
    public void onPause() {
        getRecorder().pause();
    }

    @Override
    public void onResume() {
        getRecorder().resume();
    }

    @Override
    public void onStop() {
        getRecorder().stop();
    }

}