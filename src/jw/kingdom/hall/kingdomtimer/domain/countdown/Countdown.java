package jw.kingdom.hall.kingdomtimer.domain.countdown;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplay;
import org.jetbrains.annotations.NotNull;

/**
 * All rights reserved & copyright ©
 */
public interface Countdown {
    void start(@NotNull MeetingTask task);
    int getAddedTime();
    void stop();
    void pause();
    void resume();
    void addTime(int time);
    void removeTime(int time);
    void enforceTime(int time);
    MeetingTask getTask();
    void addDisplay(TimeDisplay display);
    void removeDisplay(TimeDisplay display);
    boolean isPause();
    boolean isStop();
    int getTime();
    void addListener(CountdownListener listener);
    void removeListener(CountdownListener listener);
}
