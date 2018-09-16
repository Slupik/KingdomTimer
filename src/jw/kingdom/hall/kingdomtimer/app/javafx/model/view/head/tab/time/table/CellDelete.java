package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.head.tab.time.table;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellDelete implements Callback<TableColumn<MeetingTask, String>, TableCell<MeetingTask, String>> {

    @Override
    public TableCell<MeetingTask, String> call(TableColumn<MeetingTask, String> param) {
        return new TableCell<MeetingTask, String>() {

            final Button btn = new Button("Usuń");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction(event -> {
                        MeetingTask task = getTableView().getItems().get(getIndex());
                        getTableView().getItems().remove(task);
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        };
    }
}
