package jw.kingdom.hall.kingdomtimer.usecase.time.display;

import java.lang.reflect.Type;

/**
 * All rights reserved & copyright ©
 */
public interface TimeDisplay<T> {
    void display(int startTime, int timeToDisplay, int absoluteTimeLeft);
    void display(int time);
    void onTimeOut();
    void setTask(T task);
    void reset();
    Type getType();
}
