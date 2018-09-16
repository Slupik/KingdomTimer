package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.table;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import jw.kingdom.hall.kingdomtimer.app.javafx.translate.MeetingTaskTrans;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TaskTableController {
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private ObservableList<MeetingTask> tableData = MeetingSchedule.getInstance().getList();

    private final TableView<MeetingTask> TABLE;
    private TableColumn<MeetingTask, String> tcDelete;
    private TableColumn<MeetingTask, String> tcBuzzer;
    private TableColumn<MeetingTask, String> tcDirect;
    private TableColumn<MeetingTask, String> tcName;
    private TableColumn<MeetingTask, TimeField> tcTime;
    private TableColumn<MeetingTask, String> tcType;

    public TaskTableController(TableView<MeetingTask> table,
                               TableColumn<MeetingTask, String> tcDelete,
                               TableColumn<MeetingTask, String> tcBuzzer,
                               TableColumn<MeetingTask, String> tcDirect,
                               TableColumn<MeetingTask, String> tcName,
                               TableColumn<MeetingTask, TimeField> tcTime,
                               TableColumn<MeetingTask, String> tcType
    ) {
        TABLE = table;
        this.tcDelete = tcDelete;
        this.tcBuzzer = tcBuzzer;
        this.tcDirect = tcDirect;
        this.tcName = tcName;
        this.tcTime = tcTime;
        this.tcType = tcType;
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
            MeetingTask task = t.getTableView().getItems().get(t.getTablePosition().getRow());
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
        tcDirect.setCellFactory(new CellDirect());
        tcDirect.setSortable(false);

        tcType.setEditable(false);
        tcType.setCellFactory(new CellType());
        tcType.setSortable(false);

        setRowFactory();
    }

    private void setRowFactory() {
        TABLE.setRowFactory(tv -> {
            TableRow<MeetingTask> row = new TaskTableRow();

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
                    MeetingTask draggedItem = TABLE.getItems().remove(draggedIndex);

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
            for(MeetingTask.Type type:MeetingTask.Type.values()) {
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
}
