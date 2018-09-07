package jw.kingdom.hall.kingdomtimer.model.time.schedule;

import jw.kingdom.hall.kingdomtimer.model.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.model.task.Task;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface ScheduleController {
    void clear();
    void setTasks(List<ObservableTask> list);
    void addTask(ObservableTask task);
    void removeTask(Task task);
    List<ObservableTask> getTasks();

    void addListener(Listener listener);
    void removeListener(Listener listener);

    interface Listener {
        void onListChange(List<ObservableTask> list);
    }
}
