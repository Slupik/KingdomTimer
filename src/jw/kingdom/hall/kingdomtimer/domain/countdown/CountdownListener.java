package jw.kingdom.hall.kingdomtimer.domain.countdown;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
 */
public interface CountdownListener {
    void onStart(MeetingTask task);
    void onPause();
    void onResume();
    void onStop();
    void onTimeOut();
    void onTimeManipulate(int totalAdded, int added);
    void onEnforceTime(int time);
}
