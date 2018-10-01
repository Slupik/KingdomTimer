package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.Arrays;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class TaskListBinder implements ScheduleListener {

    private final List<TaskBean> bind;

    public TaskListBinder(List<TaskBean> bind) {
        this.bind = bind;
    }

    public TaskListBinder(List<TaskBean> bind, Schedule schedule) {
        this.bind = bind;
        schedule.addListener(this);
    }

    @Override
    public void onRemove(TaskBean task) {
        bind.remove(task);
    }

    @Override
    public void onRemove(int index, TaskBean removed) {
        bind.remove(index);
    }

    @Override
    public void onInsert(TaskBean task) {
        bind.add(task);
    }

    @Override
    public void onBulkInsert(TaskBean... task) {
        bind.addAll(Arrays.asList(task));
    }

    @Override
    public void onClear() {
        bind.clear();
    }

    @Override
    public void onReset(List<TaskBean> newList) {
        bind.clear();
        bind.addAll(newList);
    }

    @Override
    public void onMove(int elementIndex, int destIndex) {
        TaskBean task = bind.get(elementIndex);
        bind.remove(elementIndex);
        bind.add(destIndex, task);
    }
}
