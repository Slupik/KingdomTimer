package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public abstract class MeetingScheduleListener implements ScheduleListener {

    @Override
    public void onRemove(TaskBean task) {

    }

    @Override
    public void onRemove(int index, TaskBean removed) {

    }

    @Override
    public void onInsert(TaskBean task) {

    }

    @Override
    public void onBulkInsert(TaskBean... task) {

    }

    @Override
    public void onClear() {

    }

    @Override
    public void onReset(List<TaskBean> newList) {

    }

    @Override
    public void onMove(int elementIndex, int destIndex) {

    }
}
