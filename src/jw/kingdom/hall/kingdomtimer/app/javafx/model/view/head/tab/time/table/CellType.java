package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.head.tab.time.table;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.app.view.common.translate.MeetingTaskTrans;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import org.jetbrains.annotations.NotNull;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellType implements Callback<TableColumn<MeetingTask, String>, TableCell<MeetingTask, String>> {

    @Override
    public TableCell<MeetingTask, String> call(TableColumn<MeetingTask, String> param) {
        return new TableCell<MeetingTask, String>() {

            final ChoiceBox<Item> cbType = new ChoiceBox<>();

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    for(MeetingTask.Type type:MeetingTask.Type.values()) {
                        cbType.getItems().add(new Item(type));
                    }
                    cbType.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
                        Item item1 = cbType.getItems().get((Integer) number2);
                        MeetingTask task = ((MeetingTask) getTableRow().getItem());
                        if(task!=null) {
                            task.setType(item1.type);
                        }
                    });
                    MeetingTask task = ((MeetingTask) getTableRow().getItem());
                    cbType.setValue(getValue(task.getType()));
                    setGraphic(cbType);
                    setText(null);
                }
            }

            @NotNull
            private Item getValue(MeetingTask.Type type) {
                ObservableList<Item> list = cbType.getItems();
                for(Item item:list) {
                    if(item.type.equals(type)) {
                        return item;
                    }
                }
                //It's impossible to return null if everything is setup properly
                return null;
            }
        };
    }

    public class Item {
        private final MeetingTask.Type type;

        public Item(MeetingTask.Type type) {
            this.type = type;
        }

        @Override
        public String toString(){
            return MeetingTaskTrans.getForTable(type);
        }
    }
}
