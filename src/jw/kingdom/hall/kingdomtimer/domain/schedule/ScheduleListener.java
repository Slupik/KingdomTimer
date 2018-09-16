package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
 */
public interface ScheduleListener {
    void onMeetingStart();
    void onNextTask(int index, MeetingTask task);
    void onMeetingListEnd();
    void onMeetingEnd();
    void onRemove(MeetingTask task);
    void onInsert(MeetingTask task);
    void onBulkInsert(MeetingTask... task);
    void onClear();
}
