package jw.kingdom.hall.kingdomtimer.usecase.time.countdown;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.usecase.time.listener.TimeListener;
import jw.kingdom.hall.kingdomtimer.usecase.time.display.TimeDisplay;

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
