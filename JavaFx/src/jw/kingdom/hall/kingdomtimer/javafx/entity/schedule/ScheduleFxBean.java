package jw.kingdom.hall.kingdomtimer.javafx.entity.schedule;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ListChangeListener;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.javafx.mapper.MapperPojoToFxTask;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.schedule.IBEditSchedule;

import java.util.ArrayList;

/**
 * All rights reserved & copyright ©
 */
public class ScheduleFxBean extends ObservableListWrapper<TaskFxBean> {

    private final IBEditSchedule controller;
    private boolean ignoreChanges = false;

    public ScheduleFxBean(IBEditSchedule controller) {
        super(new ArrayList<>());
        this.controller = controller;
        controller.addOutput(newList -> {
            clear();
            addAll(new MapperPojoToFxTask().map(newList));
        });
        addListener((ListChangeListener<TaskFxBean>) c -> {
            if(ignoreChanges) {
                return;
            }
            ignoreChanges = true;
            controller.setSchedule(new MapperPojoToFxTask().reverseMap(this));
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
}
