package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.table;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.timedirect.BtnTimeDirectForObj;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class CellDirect implements Callback<TableColumn<TaskBean, String>, TableCell<TaskBean, String>> {

    private final AppConfig config;

    public CellDirect(AppConfig config) {
        this.config = config;
    }

    @Override
    public TableCell<TaskBean, String> call(TableColumn<TaskBean, String> param) {
        return new TableCell<TaskBean, String>() {

            final Button btn = new Button("Dół");
            BtnTimeDirectForObj controller = new BtnTimeDirectForObj(config, btn);

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    TaskBean task = getTableView().getItems().get(getIndex());
                    controller.loadTask(task);
                    controller.setMedium(false);
                    setGraphic(btn);
                    setText(null);
                }
            }
        };
    }
}
