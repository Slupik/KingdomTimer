package jw.kingdom.hall.kingdomtimer.domain.time.schedule;

import jw.kingdom.hall.kingdomtimer.model.observable.list.ObservableArray;
import jw.kingdom.hall.kingdomtimer.model.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.model.task.ObservableTask;

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

    private void notifyListenerAboutListChange() {
        for(Listener listener:listeners) {
            listener.onListChange(this.list);
        }
    }
}
