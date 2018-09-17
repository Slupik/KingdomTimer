package jw.kingdom.hall.kingdomtimer.domain.time;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface TimeListener {
    void onStart(MeetingTask task);
    void onPause();
    void onResume();
    void onStop();

    void onTimeEnforce(int enforced);
    void onTimeAdded(int totalAdded, int added);

    void onMeetingStart();
    void onMeetingEnd();

    void onScheduleChange(List<MeetingTask> newList);
}
