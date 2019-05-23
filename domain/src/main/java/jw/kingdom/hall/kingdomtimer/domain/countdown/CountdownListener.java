package jw.kingdom.hall.kingdomtimer.domain.countdown;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

/**
 * All rights reserved & copyright ©
 */
public interface CountdownListener {
    void onStart(TaskBean task);
    void onPause();
    void onResume();
    void onStop();
    void onTimeOut();
    void onTimeManipulate(int totalAdded, int added);
    void onEnforceTime(int time);
}
