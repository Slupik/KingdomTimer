package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.record;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.javafx.control.sps.SpsControllerForRecord;
import jw.kingdom.hall.kingdomtimer.javafx.control.sps.SpsControllerForTime;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TabRecordPresenter extends TabPresenter implements SpsControllerForRecord.Input {

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
    }

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {

    }

    @Override
    public Recorder getRecorder() {
        return getWindowData().getRecorder();
    }
}
