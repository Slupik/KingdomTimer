package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.recordControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jw.kingdom.hall.kingdomtimer.app.view.common.ControlledScreenImpl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class RecordController extends ControlledScreenImpl implements Initializable {

    @FXML
    VBox vbMainContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onStartAction(ActionEvent event) {

    }

    @FXML
    private void onPauseAction(ActionEvent event) {

    }

    @FXML
    private void onStopAction(ActionEvent event) {

    }

    @Override
    protected Region getMainContainer() {
        return vbMainContainer;
    }
}
