package jw.kingdom.hall.kingdomtimer.domain.time;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
 */
public interface TimeDisplay {
    void setTask(MeetingTask task);
    void display(int startTime, int timeToDisplay, int absoluteTimeLeft);
    void display(int time);
    void onTimeOut();
    void reset();
}
