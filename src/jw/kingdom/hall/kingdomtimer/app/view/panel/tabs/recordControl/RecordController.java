package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.recordControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class RecordController extends ControlledScreenImpl implements Initializable {

    @FXML
    private VBox vbMainContainer;

    @FXML
    private Label lblTime;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnStop;

    @FXML
    private CheckBox cbAutopStart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initVoiceRecordInstance();
    }

    private void initVoiceRecordInstance() {
        try {
            VoiceRecorder.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onStartAction(ActionEvent event) {
        VoiceRecorder.getInstance().start();
    }

    @FXML
    private void onPauseAction(ActionEvent event) {
        VoiceRecorder.getInstance().pause();
    }

    @FXML
    private void onStopAction(ActionEvent event) {
        VoiceRecorder.getInstance().stop();
    }

    @Override
    protected Region getMainContainer() {
        return vbMainContainer;
    }
}
