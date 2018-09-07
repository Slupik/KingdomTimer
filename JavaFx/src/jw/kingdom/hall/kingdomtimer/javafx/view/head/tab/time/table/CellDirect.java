package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.table;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.javafx.control.button.timedirect.BtnTimeDirectForObj;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellDirect implements Callback<TableColumn<TaskFxBean, String>, TableCell<TaskFxBean, String>> {

    private final Config config;

    public CellDirect(Config config) {
        this.config = config;
    }

    @Override
    public TableCell<TaskFxBean, String> call(TableColumn<TaskFxBean, String> param) {
        return new TableCell<TaskFxBean, String>() {

            final Button btn = new Button("Dół");
            BtnTimeDirectForObj controller = new BtnTimeDirectForObj(config, btn);

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    TaskFxBean task = getTableView().getItems().get(getIndex());
                    controller.loadTask(task);
                    controller.setMedium(false);
                    setGraphic(btn);
                    setText(null);
                }
            }
        };
    }
}
