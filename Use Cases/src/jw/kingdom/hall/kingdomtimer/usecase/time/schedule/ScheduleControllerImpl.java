package jw.kingdom.hall.kingdomtimer.usecase.time.schedule;

import jw.kingdom.hall.kingdomtimer.entity.observable.list.ObservableArray;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class ScheduleControllerImpl implements ScheduleController {
    private ArrayList<Listener> listeners = new ArrayList<>();
    private ObservableArray<ObservableTask> list = new ObservableArray<>();
    private boolean wasFirstTask = false;

    @Override
    public void clear() {
        this.list.clear();
        wasFirstTask = false;
        notifyListenerAboutListChange();
    }

    @Override
    public void setTasks(List<ObservableTask> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyListenerAboutListChange();
    }

    @Override
    public void addTask(ObservableTask task) {
        this.list.add(task);
        notifyListenerAboutListChange();
    }

    @Override
    public void removeTask(Task task) {
        List<ObservableTask> toRemove = new ArrayList<>();
        for(ObservableTask observableTask:list) {
            if(observableTask.getID().equals(task.getID())) {
                toRemove.add(observableTask);
            }
        }
        list.removeAll(toRemove);
        notifyListenerAboutListChange();
    }

    @Override
    public List<ObservableTask> getTasks() {
        return this.list;
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public Task bringOutFirstTask() {
        Task task = list.get(0);
        removeTask(task);
        if(!wasFirstTask) {
            wasFirstTask = true;
            notifyListenerAboutFirstTaskUse();
        } else if(list.size()==0) {
            notifyListenerAboutLastTaskUse();
        }
        return task;
    }

    private void notifyListenerAboutListChange() {
        for(Listener listener:listeners) {
            listener.onListChange(this.list);
        }
    }

    private void notifyListenerAboutFirstTaskUse() {
        for(Listener listener:listeners) {
            listener.onFirstTaskUse();
        }
    }

    private void notifyListenerAboutLastTaskUse() {
        for(Listener listener:listeners) {
            listener.onLastTaskUse();
        }
    }
}
