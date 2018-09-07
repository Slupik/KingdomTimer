package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.TabPresenter;
import jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table.TaskTableController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * All rights reserved & copyright ©
 */
public class TabTimePresenter extends TabPresenter implements TaskTableController.Data {

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
    private TableView<TaskFxBean> tvList;

    @FXML
    private TableColumn<TaskFxBean, String> tcDelete;

    @FXML
    private TableColumn<TaskFxBean, String> tcBuzzer;

    @FXML
    private TableColumn<TaskFxBean, String> tcDirect;

    @FXML
    private TableColumn<TaskFxBean, String> tcName;

    @FXML
    private TableColumn<TaskFxBean, TimeField> tcTime;

    @FXML
    private TableColumn<TaskFxBean, String> tcType;

    @Override
    public void onStart() {
        new TaskTableController(this);
        new WidgetVisibilityController(btnWidgetVisibility, getWindowsContainer());
    }

    @Override
    protected void onCreate(URL location, ResourceBundle resources) {

    }

    public void handleLoadTimeAction(ActionEvent actionEvent) {

    }

    public void handleAddTime(ActionEvent actionEvent) {

    }

    public void handleRemoveTime(ActionEvent actionEvent) {

    }

    public void handleAddTask(ActionEvent actionEvent) {

    }

    public void loadTasksOnline(ActionEvent actionEvent) {

    }

    public void loadCircuitTasksOnline(ActionEvent actionEvent) {

    }

    @Override
    public TableView<TaskFxBean> getTable() {
        return tvList;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcDelete() {
        return tcDelete;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcBuzzer() {
        return tcBuzzer;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcDirect() {
        return tcDirect;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcName() {
        return tcName;
    }

    @Override
    public TableColumn<TaskFxBean, TimeField> getTcTime() {
        return tcTime;
    }

    @Override
    public TableColumn<TaskFxBean, String> getTcType() {
        return tcType;
    }
}
