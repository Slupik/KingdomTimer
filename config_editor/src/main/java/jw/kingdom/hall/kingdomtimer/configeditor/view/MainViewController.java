package jw.kingdom.hall.kingdomtimer.configeditor.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class MainViewController implements Initializable {

    @FXML
    private ChoiceBox<String> cbSource;

    @FXML
    private TextArea taPath;

    @FXML
    private Label lblType;

    @FXML
    private Label lblPriority;

    @FXML
    private VBox vbCreateContainer;

    @FXML
    private Button btnCreate;

    @FXML
    private VBox vbChangesContainer;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnAbort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetToDefaultView();
    }

    private void resetToDefaultView() {
        showChangesContainer(false);
        showCreateContainer(false);
        lblPriority.setText("?");
        lblType.setText("?");
    }

    private void showChangesContainer(boolean show) {
        vbChangesContainer.setManaged(show);
        vbChangesContainer.setVisible(show);
    }

    private void showCreateContainer(boolean show) {
        vbCreateContainer.setManaged(show);
        vbCreateContainer.setVisible(show);
    }
}
