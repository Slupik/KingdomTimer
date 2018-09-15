package jw.kingdom.hall.kingdomtimer.usecase.time.listener;

import java.lang.reflect.Type;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface TimeListener<T> {
    void onStart(T task);
    void onPause();
    void onResume();
    void onStop();

    void onTimeEnforce(int enforced);
    void onTimeAdded(int totalAdded, int added);

    void onMeetingStart();
    void onMeetingEnd();

    void onScheduleChange(List<T> newList);

    Type getType();
}
