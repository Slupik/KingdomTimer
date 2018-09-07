package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table;

import com.sun.istack.internal.NotNull;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.javafx.temp.MeetingTaskTrans;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellType implements Callback<TableColumn<TaskFxBean, String>, TableCell<TaskFxBean, String>> {

    @Override
    public TableCell<TaskFxBean, String> call(TableColumn<TaskFxBean, String> param) {
        return new TableCell<TaskFxBean, String>() {

            final ChoiceBox<Item> cbType = new ChoiceBox<>();

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    for(TaskType type:TaskType.values()) {
                        cbType.getItems().add(new Item(type));
                    }
                    cbType.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
                        Item item1 = cbType.getItems().get((Integer) number2);
                        TaskFxBean task = ((TaskFxBean) getTableRow().getItem());
                        if(task!=null) {
                            task.setType(item1.type);
                        }
                    });
                    TaskFxBean task = ((TaskFxBean) getTableRow().getItem());
                    cbType.setValue(getValue(task.getType()));
                    setGraphic(cbType);
                    setText(null);
                }
            }

            @NotNull
            private Item getValue(TaskType type) {
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
        private final TaskType type;

        public Item(TaskType type) {
            this.type = type;
        }

        @Override
        public String toString(){
            //TODO change to more flexible translation
            return MeetingTaskTrans.getForTable(type);
        }
    }
}
