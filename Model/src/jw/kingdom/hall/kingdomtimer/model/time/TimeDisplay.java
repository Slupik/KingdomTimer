package jw.kingdom.hall.kingdomtimer.model.time;

import jw.kingdom.hall.kingdomtimer.model.task.Task;

/**
 * All rights reserved & copyright ©
 */
public interface TimeDisplay {
    void onTaskChange(Task newTask);
    void display(int startTime, int time);
}
