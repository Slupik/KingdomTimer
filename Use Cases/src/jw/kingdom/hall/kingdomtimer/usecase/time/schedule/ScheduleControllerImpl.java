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

    @Override
    public void clear() {
        this.list.clear();
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
        return task;
    }

    private void notifyListenerAboutListChange() {
        for(Listener listener:listeners) {
            listener.onListChange(this.list);
        }
    }
}
