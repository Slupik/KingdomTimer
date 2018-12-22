package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellDelete implements Callback<TableColumn<TaskBean, String>, TableCell<TaskBean, String>> {

    @Override
    public TableCell<TaskBean, String> call(TableColumn<TaskBean, String> param) {
        return new TableCell<TaskBean, String>() {

            final Button btn = new Button("Usuń");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction(event -> {
                        TaskBean task = getTableView().getItems().get(getIndex());
                        getTableView().getItems().remove(task);
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        };
    }
}
