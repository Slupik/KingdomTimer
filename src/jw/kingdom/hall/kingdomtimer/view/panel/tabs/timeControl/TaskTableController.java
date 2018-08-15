package jw.kingdom.hall.kingdomtimer.view.panel.tabs.timeControl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

/**
 * All rights reserved & copyright ©
 */
public class TaskTableController {
    private ObservableList<MeetingTask> tableData = FXCollections.observableArrayList();

    private final TableView<MeetingTask> TABLE;
    private TableColumn<MeetingTask, String> tcDelete;
    private TableColumn<MeetingTask, String> tcBuzzer;
    private TableColumn<MeetingTask, String> tcName;
    private TableColumn<MeetingTask, TimeField> tcTime;

    public TaskTableController(TableView<MeetingTask> table,
                               TableColumn<MeetingTask, String> tcDelete,
                               TableColumn<MeetingTask, String> tcBuzzer,
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
        TABLE.setEditable(true);
        TABLE.setItems(tableData);

        tcTime.setEditable(true);
        tcTime.setCellValueFactory(new PropertyValueFactory<>("tfTime"));

        tcName.setEditable(true);
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcName.setCellFactory(
                TextFieldTableCell.forTableColumn());
        tcName.setOnEditCommit(t -> {
            MeetingTask task = t.getTableView().getItems().get(t.getTablePosition().getRow());
            task.setName(t.getNewValue());
        });

        tcDelete.setEditable(false);
        tcDelete.setCellFactory(new CellDelete());

        tcBuzzer.setEditable(false);
        tcBuzzer.setCellFactory(new CellBuzzer());
    }

    public ObservableList<MeetingTask> getList() {
        return tableData;
    }
}
