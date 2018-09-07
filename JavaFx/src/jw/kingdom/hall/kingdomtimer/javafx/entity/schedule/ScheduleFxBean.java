package jw.kingdom.hall.kingdomtimer.javafx.entity.schedule;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ListChangeListener;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.model.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.model.task.Task;
import jw.kingdom.hall.kingdomtimer.model.time.schedule.ScheduleController;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class ScheduleFxBean extends ObservableListWrapper<TaskFxBean> {

    private final ScheduleController controller;
    private boolean ignoreChanges = false;

    public ScheduleFxBean(ScheduleController controller) {
        super(getAsFxList(controller.getTasks()));
        this.controller = controller;
        controller.addListener(list -> {
            if(ignoreChanges) {
                return;
            }
            ignoreChanges = true;
            clear();
            addAll(getAsFxList(list));
            ignoreChanges = false;
        });
        addListener((ListChangeListener<TaskFxBean>) c -> {
            if(ignoreChanges) {
                return;
            }
            ignoreChanges = true;
            while (c.next()) {
                if (c.wasRemoved()) {
                    List<? extends TaskFxBean> removed = c.getRemoved();
                    for(Task task:removed) {
                        controller.removeTask(task);
                    }
                }
                if (c.wasAdded()) {
                    List<? extends TaskFxBean> added = c.getAddedSubList();
                    for(TaskFxBean task:added) {
                        controller.addTask(task.getAsObservable());
                    }
                }
            }
            ignoreChanges = false;
        });
    }

    private static List<TaskFxBean> getAsFxList(List<ObservableTask> tasks) {
        List<TaskFxBean> newList = new ArrayList<>();
        for(ObservableTask task:tasks) {
            newList.add(new TaskFxBean(task));
        }
        return newList;
    }
}
