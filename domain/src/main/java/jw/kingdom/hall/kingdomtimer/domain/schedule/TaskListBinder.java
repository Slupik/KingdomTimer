package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.Arrays;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class TaskListBinder implements ScheduleListener {

    private final List<TaskBean> bind;
    private final Callback callback;

    public TaskListBinder(List<TaskBean> bind, Callback callback) {
        this.bind = bind;
        this.callback = callback;
    }

    public TaskListBinder(List<TaskBean> bind, Schedule schedule, Callback callback) {
        this.bind = bind;
        this.callback = callback;
        schedule.addListener(this);
    }

    @Override
    public void onRemove(TaskBean task) {
        callback.onEditionStart();
        bind.remove(task);
        callback.onEditionEnd();
    }

    @Override
    public void onRemove(int index, TaskBean removed) {
        callback.onEditionStart();
        bind.remove(index);
        callback.onEditionEnd();
    }

    @Override
    public void onInsert(TaskBean task) {
        callback.onEditionStart();
        bind.add(task);
        callback.onEditionEnd();
    }

    @Override
    public void onBulkInsert(TaskBean... task) {
        callback.onEditionStart();
        bind.addAll(Arrays.asList(task));
        callback.onEditionEnd();
    }

    @Override
    public void onClear() {
        callback.onEditionStart();
        bind.clear();
        callback.onEditionEnd();
    }

    @Override
    public void onReset(List<TaskBean> newList) {
        callback.onEditionStart();
        bind.clear();
        bind.addAll(newList);
        callback.onEditionEnd();
    }

    @Override
    public void onMove(int elementIndex, int destIndex) {
        callback.onEditionStart();
        TaskBean task = bind.get(elementIndex);
        bind.remove(elementIndex);
        bind.add(destIndex, task);
        callback.onEditionEnd();
    }

    public interface Callback {

        void onEditionStart();
        void onEditionEnd();

    }
}
