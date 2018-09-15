package jw.kingdom.hall.kingdomtimer.usecase.time.listener;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface TimeListener {
    void onStart(Task task);
    void onPause();
    void onResume();
    void onStop();

    void onTimeEnforce(int enforced);
    void onTimeAdded(int totalAdded, int added);

    void onMeetingStart();
    void onMeetingEnd();

    void onScheduleChange(List<Task> newList);
}
