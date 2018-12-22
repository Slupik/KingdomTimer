package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.javafx.translate.MeetingTaskTrans;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import org.jetbrains.annotations.NotNull;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellType implements Callback<TableColumn<TaskBean, String>, TableCell<TaskBean, String>> {

    @Override
    public TableCell<TaskBean, String> call(TableColumn<TaskBean, String> param) {
        return new TableCell<TaskBean, String>() {

            final ChoiceBox<Item> cbType = new ChoiceBox<>();

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    for(TaskBean.Type type: TaskBean.Type.values()) {
                        cbType.getItems().add(new Item(type));
                    }
                    cbType.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, number2) -> {
                        Item item1 = cbType.getItems().get((Integer) number2);
                        TaskBean task = ((TaskBean) getTableRow().getItem());
                        if(task!=null) {
                            task.setType(item1.type);
                        }
                    });
                    TaskBean task = ((TaskBean) getTableRow().getItem());
                    cbType.setValue(getValue(task.getType()));
                    setGraphic(cbType);
                    setText(null);
                }
            }

            @NotNull
            private Item getValue(TaskBean.Type type) {
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
        private final TaskBean.Type type;

        public Item(TaskBean.Type type) {
            this.type = type;
        }

        @Override
        public String toString(){
            return MeetingTaskTrans.getForTable(type);
        }
    }
}
