package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.table;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
 */
public class CellBuzzer implements Callback<TableColumn<MeetingTask, String>, TableCell<MeetingTask, String>> {

    @Override
    public TableCell<MeetingTask, String> call(TableColumn<MeetingTask, String> param) {
        return new TableCell<MeetingTask, String>() {

            final CheckBox box = new CheckBox("Dźwięk");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    box.setOnAction(event -> {
                        MeetingTask task = getTableView().getItems().get(getIndex());
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
