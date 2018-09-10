package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.record;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.model.ConfigEditable;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.javafx.control.sps.SpsControllerForRecord;
import jw.kingdom.hall.kingdomtimer.javafx.control.sps.SpsControllerForTime;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TabRecordPresenter extends TabPresenter implements SpsControllerForRecord.Input, RecordCutController.Input {

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

    @Override
    public void onStart() {
        hbControlsContainer.getChildren().add(new SpsControllerForRecord(this).getView());
        getRecorder().addDisplay(new TimeDisplayController(lblTime)::display);
        loadConfig();
        bindConfig();
        new RecordCutController(this);
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

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {

    }

    private Config getConfig() {
        return getWindowData().getConfig();
    }

    @Override
    public Recorder getRecorder() {
        return getWindowData().getRecorder();
    }

    @Override
    public ScheduleController getSchedule() {
        return getWindowData().getSchedule();
    }

    @Override
    public CountdownController getCountdown() {
        return getWindowData().getCountdown();
    }

    @Override
    public boolean isAutoPilotOn() {
        return cbAutopilot.isSelected();
    }

    @Override
    public boolean isAutoSeparateOn() {
        return cbAutoSeparate.isSelected();
    }
}
