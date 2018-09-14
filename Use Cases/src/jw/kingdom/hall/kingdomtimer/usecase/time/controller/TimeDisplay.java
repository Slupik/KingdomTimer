package jw.kingdom.hall.kingdomtimer.usecase.time.controller;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;

/**
 * All rights reserved & copyright ©
 */
public interface TimeDisplay {
    void display(int startTime, int timeToDisplay, int absoluteTimeLeft);
    void onTimeOut();
    void setTask(Task task);
    void reset();
}
