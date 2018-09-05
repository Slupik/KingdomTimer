package jw.kingdom.hall.kingdomtimer.model.meeting;

import jw.kingdom.hall.kingdomtimer.model.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.model.task.Task;
import jw.kingdom.hall.kingdomtimer.model.observable.list.ObservableArray;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class TaskList extends ObservableArray<ObservableTask> {
    List<Task> getTasksList() {
        return new ArrayList<>(LIST);
    }
}
