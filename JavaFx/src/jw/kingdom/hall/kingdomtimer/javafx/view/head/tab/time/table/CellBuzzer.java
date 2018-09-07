package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellBuzzer implements Callback<TableColumn<TaskFxBean, String>, TableCell<TaskFxBean, String>> {

    @Override
    public TableCell<TaskFxBean, String> call(TableColumn<TaskFxBean, String> param) {
        return new TableCell<TaskFxBean, String>() {

            final CheckBox box = new CheckBox("Dźwięk");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    box.setOnAction(event -> {
                        TaskFxBean task = getTableView().getItems().get(getIndex());
                        task.setUseBuzzer(box.isSelected());
                    });
                    box.setSelected(getTableView().getItems().get(getIndex()).isUseBuzzer());
                    setGraphic(box);
                    setText(null);
                }
            }
        };
    }
}
