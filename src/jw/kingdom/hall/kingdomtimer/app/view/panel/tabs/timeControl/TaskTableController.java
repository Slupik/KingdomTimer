package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.app.view.common.translate.MeetingTaskTrans;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.cell.CellBuzzer;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.cell.CellDelete;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.cell.CellDirect;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.cell.CellType;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

/**
 * All rights reserved & copyright ©
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
            TableRow<MeetingTask> row = new TableRow<MeetingTask>() {
                private MeetingTask lastTask;
                private ChangeListener<MeetingTask.Type> backgroundChanger = (observable, oldValue, newValue) -> {
                    setRowColor(this, newValue);
                };

                @Override
                public void updateItem(MeetingTask item, boolean empty) {
                    super.updateItem(item, empty);
                    Platform.runLater(()->{
                        if(lastTask!=null) {
                            lastTask.typeProperty().removeListener(backgroundChanger);
                        }
                        lastTask = item;
                        if(item!=null) {
                            item.typeProperty().addListener(backgroundChanger);
                        }
                    });
                    if(getItem()!=null) {
                        setRowColor(this, getItem().getType());
                    }
                }
            };

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

    private void setRowColor(TableRow<MeetingTask> row, MeetingTask.Type type) {
        row.setBackground(getRowBackground(type));
    }

    private Background getRowBackground(MeetingTask.Type type) {
        switch (type) {
            case UNKNOWN: {
               return new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case NONE: {
                return Background.EMPTY;
            }
            case TREASURES: {
                return new Background(new BackgroundFill(Color.web("#ffca28"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case MINISTRY: {
                return new Background(new BackgroundFill(Color.web("#ef9a9a"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case LIVING: {
                return new Background(new BackgroundFill(Color.web("#81c784"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case LECTURE: {
                return new Background(new BackgroundFill(Color.web("#d1c4e9"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case WATCHTOWER: {
                return new Background(new BackgroundFill(Color.web("#81d4fa"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case OVERSEER: {
                return new Background(new BackgroundFill(Color.web("#ffee58"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            case OTHER: {
                return new Background(new BackgroundFill(Color.web("#b0bec5"), CornerRadii.EMPTY, Insets.EMPTY));
            }
            default: {
                return Background.EMPTY;
            }
        }
    }
}
