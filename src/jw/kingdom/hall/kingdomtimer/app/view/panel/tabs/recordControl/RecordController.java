package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.recordControl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.app.view.common.custom.sps.StartPauseStopView;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingScheduleListener;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class RecordController extends ControlledScreenImpl implements Initializable, StartPauseStopView.Listener, Recorder.Listener {

    @FXML
    private VBox vbMainContainer;

    @FXML
    private Label lblTime;

    @FXML
    private HBox hbControlsContainer;

    @FXML
    private CheckBox cbAutopilot;

    private TimeDisplayController controller;
    private StartPauseStopView spsView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initVoiceRecordInstance();

        spsView = new StartPauseStopView();
        hbControlsContainer.getChildren().add(spsView);
        spsView.addListener(this);

        controller = new TimeDisplayController(lblTime);
        controller.setTime(0);

        MeetingSchedule.getInstance().addListener(new MeetingScheduleListener() {
            @Override
            public void onMeetingStart() {
                super.onMeetingStart();
                if(isAutopilotOn()) {
                    spsView.start();
                }
            }

            @Override
            public void onMeetingEnd() {
                super.onMeetingEnd();
                if(isAutopilotOn()) {
                    spsView.stop();
                }
            }
        });

        loadConfig();
        bindConfig();
    }

    private void bindConfig() {
        cbAutopilot.selectedProperty().addListener((observable, oldValue, newValue) ->
                AppConfig.getInstance().setEnabledAutopilot(newValue));
    }

    private void loadConfig() {
        cbAutopilot.setSelected(AppConfig.getInstance().isEnabledAutopilot());
    }

    private void initVoiceRecordInstance() {
        try {
            VoiceRecorder.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        VoiceRecorder.getInstance().addListener(this);
    }

    private boolean isAutopilotOn(){
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

    @Override
    public void onNewTime(int seconds) {
        controller.setTime(seconds);
    }
}
