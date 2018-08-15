package jw.kingdom.hall.kingdomtimer.view.panel.tabs.timeControl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

/**
 * All rights reserved & copyright ©
 */
public class TaskTableController {
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

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

        makeRowsDraggable();
    }

    private void makeRowsDraggable() {
        TABLE.setRowFactory(tv -> {
            TableRow<MeetingTask> row = new TableRow<>();

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

            return row ;
        });
    }

    public ObservableList<MeetingTask> getList() {
        return tableData;
    }
}
