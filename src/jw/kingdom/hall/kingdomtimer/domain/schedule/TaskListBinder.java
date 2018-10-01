package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.Arrays;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class TaskListBinder implements ScheduleListener {

    private final List<MeetingTask> bind;

    public TaskListBinder(List<MeetingTask> bind) {
        this.bind = bind;
    }

    public TaskListBinder(List<MeetingTask> bind, Schedule schedule) {
        this.bind = bind;
        schedule.addListener(this);
    }

    @Override
    public void onRemove(MeetingTask task) {
        bind.remove(task);
    }

    @Override
    public void onRemove(int index, MeetingTask removed) {
        bind.remove(index);
    }

    @Override
    public void onInsert(MeetingTask task) {
        bind.add(task);
    }

    @Override
    public void onBulkInsert(MeetingTask... task) {
        bind.addAll(Arrays.asList(task));
    }

    @Override
    public void onClear() {
        bind.clear();
    }

    @Override
    public void onReset(List<MeetingTask> newList) {
        bind.clear();
        bind.addAll(newList);
    }

    @Override
    public void onMove(int elementIndex, int destIndex) {
        MeetingTask task = bind.get(elementIndex);
        bind.remove(elementIndex);
        bind.add(destIndex, task);
    }
}
