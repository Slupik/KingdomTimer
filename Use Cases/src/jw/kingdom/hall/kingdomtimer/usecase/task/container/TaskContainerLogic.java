package jw.kingdom.hall.kingdomtimer.usecase.task.container;

import jw.kingdom.hall.kingdomtimer.entity.task.TaskContainer;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class TaskContainerLogic implements TaskContainer {

    private final List<Task> LIST = new ArrayList<>();

    @Override
    public List<Task> getList() {
        return LIST;
    }

    @Override
    public void setList(List<Task> task) {
        LIST.clear();
        LIST.addAll(task);
    }

    @Override
    public void clear() {

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void add(Task task) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public Task get(String id) {
        return null;
    }
}
