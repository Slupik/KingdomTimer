package jw.kingdom.hall.kingdomtimer.model.time.countdown;

import jw.kingdom.hall.kingdomtimer.model.task.Task;
import jw.kingdom.hall.kingdomtimer.model.time.TimeDisplay;

/**
 * All rights reserved & copyright ©
 */
public interface CountdownController {

    void start(Task task);
    void stop();
    void pause();
    void resume();
    CountdownState getState();
    Task getActualTask();

    int getAddedTime();
    void addTime(int time);
    void subtractTime(int time);

    int getTime();
    void enforceTime(int time);

    void addListener(Listener listener);
    void removeListener(Listener listener);

    void addTimeDisplay(TimeDisplay timeDisplay);
    void removeTimeDisplay(TimeDisplay timeDisplay);

    interface Listener {
        void onTaskStart(Task task);
        void onTimeChange(int time);
        void onStateChanged(CountdownState last, CountdownState now);

        void onAddedTimeChange(int addedSeconds);
        void onEnforceTime(int enforcedTime);
    }
}
