package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public abstract class MeetingScheduleListener implements ScheduleListener {

    @Override
    public void onRemove(TaskBean task) {
        onModifySchedule();
    }

    @Override
    public void onRemove(int index, TaskBean removed) {
        onModifySchedule();
    }

    @Override
    public void onInsert(TaskBean task) {
        onModifySchedule();
    }

    @Override
    public void onBulkInsert(TaskBean... task) {
        onModifySchedule();
    }

    @Override
    public void onClear() {
        onModifySchedule();
    }

    @Override
    public void onReset(List<TaskBean> newList) {
        onModifySchedule();
    }

    @Override
    public void onMove(int elementIndex, int destIndex) {
        onModifySchedule();
    }

    protected void onModifySchedule() {

    }
}
