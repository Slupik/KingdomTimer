package jw.kingdom.hall.kingdomtimer.entity.meeting;

import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.observable.array.ObservableArray;

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
