package jw.kingdom.hall.kingdomtimer.domain.time;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

/**
 * All rights reserved & copyright ©
 */
public interface TimeDisplay {
    void setTask(TaskBean task);
    void display(int startTime, int timeToDisplay, int absoluteTimeLeft);
    void display(int time);
    void onTimeOut();
    void reset();
}
