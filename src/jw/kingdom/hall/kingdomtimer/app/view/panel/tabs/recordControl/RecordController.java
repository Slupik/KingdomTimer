package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.recordControl;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.view.common.custom.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.DefaultVoiceRecorderListener;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingScheduleListener;
import jw.kingdom.hall.kingdomtimer.recorder.RecordListenerProxy;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class RecordController extends ControlledScreenImpl implements Initializable, StartPauseStopView.Listener {

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
    public void initialize(URL location, ResourceBundle resources) {
        initVoiceRecordInstance();

        spsView = new StartPauseStopView();
        hbControlsContainer.getChildren().add(spsView);
        spsView.addListener(this);
        VoiceRecorder.getInstance().addListener(new DefaultVoiceRecorderListener() {
            @Override
            public void onStart() {
                super.onStart();
                spsView.setupForStart();
            }

            @Override
            public void onPause(boolean isPause) {
                super.onPause(isPause);
                if (isPause) {
                    spsView.setupForPause();
                } else {
                    spsView.setupForUnPause();
                }
            }

            @Override
            public void onStop() {
                super.onStop();
                spsView.setupForStop();
            }
        });

        controller = new TimeDisplayController(lblTime);
        controller.setTime(0);

        MeetingSchedule.getInstance().addListener(new MeetingScheduleListener() {
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
                    VoiceRecorder.getInstance().stop();
                    VoiceRecorder.getInstance().start();
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
            AppConfig.getInstance().setEnabledAutopilot(newValue);
            cbAutoSeparate.setDisable(!newValue);
        });
        cbAutoSeparate.selectedProperty().addListener((observable, oldValue, newValue) ->
                AppConfig.getInstance().setEnabledAutoSeparate(newValue));
    }

    private void loadConfig() {
        cbAutopilot.setSelected(AppConfig.getInstance().isEnabledAutopilot());
        cbAutoSeparate.setSelected(AppConfig.getInstance().isAutoSeparate());
        tfPath.setText(AppConfig.getInstance().getRecordDestPath());
    }

    private void initVoiceRecordInstance() {
        try {
            VoiceRecorder.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        VoiceRecorder.getInstance().addListener(new RecordListenerProxy() {
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
    protected Region getMainContainer() {
        return vbMainContainer;
    }

    @Override
    public void onStart() {
        VoiceRecorder.getInstance().start();
    }

    @Override
    public void onPause() {
        VoiceRecorder.getInstance().setPause(true);
    }

    @Override
    public void onUnPause() {
        VoiceRecorder.getInstance().setPause(false);
    }

    @Override
    public void onStop() {
        VoiceRecorder.getInstance().stop();
    }

}