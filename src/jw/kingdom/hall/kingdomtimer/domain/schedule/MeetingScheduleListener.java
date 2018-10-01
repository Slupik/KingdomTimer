package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public abstract class MeetingScheduleListener implements ScheduleListener {

    @Override
    public void onRemove(MeetingTask task) {

    }

    @Override
    public void onRemove(int index, MeetingTask removed) {

    }

    @Override
    public void onInsert(MeetingTask task) {

    }

    @Override
    public void onBulkInsert(MeetingTask... task) {

    }

    @Override
    public void onClear() {

    }

    @Override
    public void onReset(List<MeetingTask> newList) {

    }

    @Override
    public void onMove(int elementIndex, int destIndex) {

    }
}
