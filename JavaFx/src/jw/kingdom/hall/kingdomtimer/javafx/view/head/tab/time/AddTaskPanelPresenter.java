package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.javafx.control.time.direct.BtnTimeDirectForPanel;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

/**
 * All rights reserved & copyright ©
 */
class AddTaskPanelPresenter {

    private final Data data;

    AddTaskPanelPresenter(Data data) {
        this.data = data;
    }

    void handleAddTask() {
        ObservableTask task = getTaskFromFields();
        data.getSchedule().addTask(task);

        clearFields();
    }

    private ObservableTask getTaskFromFields() {
        ObservableTask task = new TaskBean();
        task.setName(data.getTfName().getText());
        task.setSeconds(data.getAtfTime().getAllSeconds());
        task.setUseBuzzer(data.getCbBuzzer().isSelected());
        task.setDirectDown(data.getTimeDirectController().isDirectDown());
        task.setType(TaskType.UNKNOWN);
        return task;
    }

    private void clearFields() {
        data.getAtfTime().setSeconds(0);
        data.getTfName().setText("");
        data.getCbBuzzer().setSelected(false);
        data.getTimeDirectController().setDirectDown(data.getConfig().isDirectDown());
    }

    interface Data {
        TextField getTfName();
        TimeField getAtfTime();
        CheckBox getCbBuzzer();
        BtnTimeDirectForPanel getTimeDirectController();
        ScheduleController getSchedule();
        Config getConfig();
    }
}
