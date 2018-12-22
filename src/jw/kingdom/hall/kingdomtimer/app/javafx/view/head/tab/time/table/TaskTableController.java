package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.table;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import jw.kingdom.hall.kingdomtimer.app.javafx.translate.MeetingTaskTrans;
import jw.kingdom.hall.kingdomtimer.domain.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.schedule.TaskListBinder;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TaskTableController {
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private ObservableList<TaskBean> tableData;

    private final TableView<TaskBean> TABLE;
    private TableColumn<TaskBean, String> tcDelete;
    private TableColumn<TaskBean, String> tcBuzzer;
    private TableColumn<TaskBean, String> tcDirect;
    private TableColumn<TaskBean, String> tcName;
    private TableColumn<TaskBean, String> tcTime;
    private TableColumn<TaskBean, String> tcType;
    private AppConfig config;
    private final Schedule schedule;
    private boolean disableEditList = false;

    public TaskTableController(Schedule schedule,
                               AppConfig config,
                               TableView<TaskBean> table,
                               TableColumn<TaskBean, String> tcDelete,
                               TableColumn<TaskBean, String> tcBuzzer,
                               TableColumn<TaskBean, String> tcDirect,
                               TableColumn<TaskBean, String> tcName,
                               TableColumn<TaskBean, String> tcTime,
                               TableColumn<TaskBean, String> tcType
    ) {
        this.schedule = schedule;
        initList();
        this.config = config;
        TABLE = table;
        this.tcDelete = tcDelete;
        this.tcBuzzer = tcBuzzer;
        this.tcDirect = tcDirect;
        this.tcName = tcName;
        this.tcTime = tcTime;
        this.tcType = tcType;
        initTable();
    }

    private void initList() {
        tableData = FXCollections.observableArrayList();
        schedule.addListener(new TaskListBinder(tableData) {
            @Override
            public void onMove(int elementIndex, int destIndex) {
                if (disableEditList) {
                    return;
                }
                super.onMove(elementIndex, destIndex);
            }
        });
        tableData.addListener((ListChangeListener<TaskBean>) c -> {
            while(c.next()){
                if(c.wasRemoved()) {
                    List<? extends TaskBean> removed = c.getRemoved();
                    for(TaskBean item:removed) {
                        schedule.removeTask(item);
                    }
                }
            }
        });
    }

    private void initTable() {
        TABLE.setEditable(true);
        TABLE.setItems(tableData);

        tcTime.setEditable(true);
        tcTime.setCellFactory(new CellTime());
        tcTime.setSortable(false);

        tcName.setEditable(true);
        tcName.setCellValueFactory(new PropertyValueFactory<>(TaskBean.PropertyName.NAME));
        tcName.setCellFactory(
                TextFieldTableCell.forTableColumn());
        tcName.setOnEditCommit(t -> {
            TaskBean task = t.getTableView().getItems().get(t.getTablePosition().getRow());
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
        tcDirect.setCellFactory(new CellDirect(getConfig()));
        tcDirect.setSortable(false);

        tcType.setEditable(false);
        tcType.setCellFactory(new CellType());
        tcType.setSortable(false);

        setRowFactory();
    }

    private void setRowFactory() {
        TABLE.setRowFactory(tv -> {
            TableRow<TaskBean> row = new TaskTableRow();

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
                    TaskBean draggedItem = TABLE.getItems().remove(draggedIndex);

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

                    disableEditList = true;
                    schedule.moveElement(draggedIndex, dropIndex);
                    disableEditList = false;
                }
            });

            final ContextMenu rowMenu = new ContextMenu();
            MenuItem typeHeader = new MenuItem("Wybierz typ:");
            rowMenu.getItems().add(typeHeader);
            for(TaskBean.Type type: TaskBean.Type.values()) {
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

    private AppConfig getConfig() {
        return config;
    }
}
