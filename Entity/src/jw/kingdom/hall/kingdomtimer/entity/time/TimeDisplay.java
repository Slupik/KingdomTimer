package jw.kingdom.hall.kingdomtimer.entity.time;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;

/**
 * All rights reserved & copyright ©
 */
public interface TimeDisplay {
    void onTaskChange(Task newTask);
    void display(int startTime, int time);
}
