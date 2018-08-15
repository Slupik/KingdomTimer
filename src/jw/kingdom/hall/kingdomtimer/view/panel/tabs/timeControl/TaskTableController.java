package jw.kingdom.hall.kingdomtimer.view.panel.tabs.timeControl;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.view.common.controller.TimeDisplayController;

/**
 * All rights reserved & copyright ©
 */
public class TaskTableController {
    private final TableView<MeetingTask> TABLE;
    private TableColumn<MeetingTask, Button> tcDelete;
    private TableColumn<MeetingTask, Button> tcBuzzer;
    private TableColumn<MeetingTask, String> tcName;
    private TableColumn<MeetingTask, TimeField> tcTime;

    public TaskTableController(TableView<MeetingTask> table,
                               TableColumn<MeetingTask, Button> tcDelete,
                               TableColumn<MeetingTask, Button> tcBuzzer,
                               TableColumn<MeetingTask, String> tcName,
                               TableColumn<MeetingTask, TimeField> tcTime) {
        TABLE = table;
        this.tcDelete = tcDelete;
        this.tcBuzzer = tcBuzzer;
        this.tcName = tcName;
        this.tcTime = tcTime;
        init();
    }

    private void init() {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
