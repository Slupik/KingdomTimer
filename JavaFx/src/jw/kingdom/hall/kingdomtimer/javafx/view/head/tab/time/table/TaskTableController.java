package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.javafx.entity.schedule.ScheduleFxBean;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.javafx.temp.MeetingTaskTrans;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.schedule.IBEditSchedule;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TaskTableController {
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private final Config config;
    private final ObservableList<TaskFxBean> tableData;

    private final TableView<TaskFxBean> TABLE;
    private TableColumn<TaskFxBean, String> tcDelete;
    private TableColumn<TaskFxBean, String> tcBuzzer;
    private TableColumn<TaskFxBean, String> tcDirect;
    private TableColumn<TaskFxBean, String> tcName;
    private TableColumn<TaskFxBean, TimeField> tcTime;
    private TableColumn<TaskFxBean, String> tcType;

    public TaskTableController(Config config,
                               IBEditSchedule schedule,
                               TableView<TaskFxBean> table,
                               TableColumn<TaskFxBean, String> tcDelete,
                               TableColumn<TaskFxBean, String> tcBuzzer,
                               TableColumn<TaskFxBean, String> tcDirect,
                               TableColumn<TaskFxBean, String> tcName,
                               TableColumn<TaskFxBean, TimeField> tcTime,
                               TableColumn<TaskFxBean, String> tcType
    ) {
        this.config = config;
        this.tableData = new ScheduleFxBean(schedule);
        this.TABLE = table;
        this.tcDelete = tcDelete;
        this.tcBuzzer = tcBuzzer;
        this.tcDirect = tcDirect;
        this.tcName = tcName;
        this.tcTime = tcTime;
        this.tcType = tcType;
        init();
    }

    public TaskTableController(Data data) {
        this.config = data.getConfig();
        this.tableData = new ScheduleFxBean(data.getScheduleEditor());
        this.TABLE = data.getTable();
        this.tcDelete = data.getTcDelete();
        this.tcBuzzer = data.getTcBuzzer();
        this.tcDirect = data.getTcDirect();
        this.tcName = data.getTcName();
        this.tcTime = data.getTcTime();
        this.tcType = data.getTcType();
        init();
    }

    private void init() {
        TABLE.setEditable(true);
        TABLE.setItems(tableData);

        tcTime.setEditable(true);
        tcTime.setCellValueFactory(new PropertyValueFactory<>("tfTime"));
        tcTime.setSortable(false);

        tcName.setEditable(true);
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcName.setCellFactory(
                TextFieldTableCell.forTableColumn());
        tcName.setOnEditCommit(t -> {
            TaskFxBean task = t.getTableView().getItems().get(t.getTablePosition().getRow());
            task.setName(t.getNewValue());
        });
        tcName.setSortable(false);

        tcDelete.setEditable(false);
        tcDelete.setCellFactory(new CellDelete());
        tcDelete.setSortable(false);

        tcBuzzer.setEditable(false);
        tcBuzzer.setCellFactory(new CellBuzzer());
        tcBuzzer.setSortable(false);

        tcDirect.setEditable(false);
        tcDirect.setCellFactory(new CellDirect(config));
        tcDirect.setSortable(false);

        tcType.setEditable(false);
        tcType.setCellFactory(new CellType());
        tcType.setSortable(false);

        setRowFactory();
    }

    private void setRowFactory() {
        TABLE.setRowFactory(tv -> {
            TableRow<TaskFxBean> row = new TaskTableRow();

            row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != (Integer) db.getContent(SERIALIZED_MIME_TYPE)) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    TaskFxBean draggedItem = TABLE.getItems().remove(draggedIndex);

                    int dropIndex ;

                    if (row.isEmpty()) {
                        dropIndex = TABLE.getItems().size() ;
                    } else {
                        dropIndex = row.getIndex();
                    }

                    TABLE.getItems().add(dropIndex, draggedItem);

                    event.setDropCompleted(true);
                    TABLE.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            final ContextMenu rowMenu = new ContextMenu();
            MenuItem typeHeader = new MenuItem("Wybierz typ:");
            rowMenu.getItems().add(typeHeader);
            for(TaskType type: TaskType.values()) {
                MenuItem item = new MenuItem("     "+ MeetingTaskTrans.getForTable(type));
                item.setOnAction(event -> {
                    row.getItem().setType(type);
                });
                rowMenu.getItems().add(item);
            }

            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu)null));

            return row ;
        });
    }
    
    public interface Data {
        Config getConfig();
        IBEditSchedule getScheduleEditor();
        TableView<TaskFxBean> getTable();
        TableColumn<TaskFxBean, String> getTcDelete();
        TableColumn<TaskFxBean, String> getTcBuzzer();
        TableColumn<TaskFxBean, String> getTcDirect();
        TableColumn<TaskFxBean, String> getTcName();
        TableColumn<TaskFxBean, TimeField> getTcTime();
        TableColumn<TaskFxBean, String> getTcType();
    }
}
