package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
 */
public abstract class MeetingScheduleListener implements MeetingSchedule.Listener {
    @Override
    public void onMeetingStart() {

    }

    @Override
    public void onNextTask(int index, MeetingTask task) {

    }

    @Override
    public void onMeetingEnd() {

    }

    @Override
    public void onMeetingListEnd() {

    }

    @Override
    public void onRemove(MeetingTask task) {

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
}
