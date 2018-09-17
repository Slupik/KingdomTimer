package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface ScheduleListener {
    void onRemove(MeetingTask task);
    void onInsert(MeetingTask task);
    void onBulkInsert(MeetingTask... task);
    void onClear();
    void onReset(List<MeetingTask> newList);
}
