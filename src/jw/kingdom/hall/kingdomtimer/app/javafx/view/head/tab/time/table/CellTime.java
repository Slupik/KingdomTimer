package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellTime implements Callback<TableColumn<MeetingTask, String>, TableCell<MeetingTask, String>> {

    @Override
    public TableCell<MeetingTask, String> call(TableColumn<MeetingTask, String> param) {
        return new TableCell<MeetingTask, String>() {

            final TimeField field = new TimeField();

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    field.textProperty().addListener((observable, oldValue, newValue) -> {
                        MeetingTask task = getTableView().getItems().get(getIndex());
                        task.setTime(field.getAllSeconds());
                    });
                    field.setSeconds(getTableView().getItems().get(getIndex()).getTime());
                    setGraphic(field);
                    setText(null);
                }
            }
        };
    }
}
