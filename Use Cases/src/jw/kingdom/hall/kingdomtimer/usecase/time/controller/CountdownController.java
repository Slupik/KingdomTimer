package jw.kingdom.hall.kingdomtimer.usecase.time.controller;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;

/**
 * All rights reserved & copyright ©
 */
public interface CountdownController {
    void start(Task task);
    void stop();
    void pause();
    void resume();

    Task getTask();

    int getAddedTime();
    void addTime(int time);

    int getTime();
    void enforceTime(int time);

    void addDisplay(TimeDisplay timeDisplay);
    void removeDisplay(TimeDisplay timeDisplay);
    void addListener(TimeListener listener);
    void removeListener(TimeListener listener);
}
