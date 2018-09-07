package jw.kingdom.hall.kingdomtimer.javafx.entity.schedule;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ListChangeListener;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;

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
            //TODO make this more efficient
            clear();
            addAll(getAsFxList(list));
            ignoreChanges = false;
        });
        addListener((ListChangeListener<TaskFxBean>) c -> {
            if(ignoreChanges) {
                return;
            }
            ignoreChanges = true;
            controller.setTasks(getThisAsObservableList());
            //TODO repair code below
//            while (c.next()) {
//                if (c.wasRemoved()) {
//                    System.out.println("wasRemoved");
//                    List<? extends TaskFxBean> removed = c.getRemoved();
//                    for(Task task:removed) {
//                        controller.removeTask(task);
//                    }
//                }
//                if (c.wasAdded()) {
//                    System.out.println("wasAdded");
//                    List<? extends TaskFxBean> added = c.getAddedSubList();
//                    for(TaskFxBean task:added) {
//                        controller.addTask(task.getAsObservable());
//                    }
//                }
//                if(c.wasPermutated()) {
//                    System.out.println("wasPermutated");
//                }
//                if(c.wasReplaced()) {
//                    System.out.println("wasReplaced");
//                }
//                if(c.wasUpdated()) {
//                    System.out.println("wasUpdated");
//                }
//            }
            ignoreChanges = false;
        });
    }

    private List<ObservableTask> getThisAsObservableList() {
        List<ObservableTask> list = new ArrayList<>();
        for(TaskFxBean task:this) {
            list.add(task.getAsObservable());
        }
        return list;
    }

    private static List<TaskFxBean> getAsFxList(List<ObservableTask> tasks) {
        List<TaskFxBean> newList = new ArrayList<>();
        for(ObservableTask task:tasks) {
            newList.add(new TaskFxBean(task));
        }
        return newList;
    }
}
