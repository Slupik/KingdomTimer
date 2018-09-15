package jw.kingdom.hall.kingdomtimer.entity.task;

import jw.kingdom.hall.kingdomtimer.entity.observable.list.ListChangeListener;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.ObservableList;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.change.ChangeFactory;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.change.ChangeType;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.change.ListChanges;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * All rights reserved & copyright ©
 */
public class TaskList implements TaskContainer {

    private final ObservableList<Task> list = new ObservableList<>();
    private boolean ignore = false;
    protected final List<ListChangeListener<Task>> listeners = new ArrayList<>();

    public TaskList() {
        list.addListener((list, changes) -> {
            if(ignore) {
                return;
            }
            notifyAboutChange(list, changes);
        });
    }

    @Override
    public List<Task> getList() {
        return list;
    }

    @Override
    public void setList(List<Task> task) {
        ignore = true;
        list.clear();
        list.addAll(task);
        ignore = false;
        ListChanges<Task> changes = ChangeFactory.makeListChange(ChangeType.SET, task);
        notifyAboutChange(list, changes);

    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void remove(String id) {
        Task task = get(id);
        if(task != null) {
            list.remove(task);
        }
    }

    @Override
    public void add(Task task) {
        list.add(task);
    }

    @Override
    public void update(Task task) {
        if(task!=null) {
            Task taskOnList = get(task.getID());
            if(taskOnList!=null && !task.equals(taskOnList)) {
                taskOnList.setType(task.getType());
                taskOnList.setName(task.getName());
                taskOnList.setSeconds(task.getSeconds());
                taskOnList.setDirectDown(task.isDirectDown());
                taskOnList.setUseBuzzer(task.isUseBuzzer());
                taskOnList.setStudentTalk(task.isStudentTalk());

                ListChanges<Task> changes = ChangeFactory.makeListChange(ChangeType.UPDATE, taskOnList);
                notifyAboutChange(list, changes);
            }
        }
    }

    @Override
    public Task get(String id) {
        for(Task task:list) {
            if(Objects.equals(task.getID(), id)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public Task get(int index) {
        return list.get(index);
    }

    @Override
    public void addListener(ListChangeListener<Task> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ListChangeListener<Task>listener) {
        listeners.remove(listener);
    }

    private void notifyAboutChange(List<Task> list, ListChanges<Task> changes) {
        for(ListChangeListener<Task> listener:listeners) {
            listener.onChange(list, changes);
        }
    }
}
