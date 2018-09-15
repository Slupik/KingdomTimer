package jw.kingdom.hall.kingdomtimer.usecase.time.display;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * All rights reserved & copyright ©
 */
public abstract class TimeDisplayProxy<T> implements TimeDisplay<T> {

    @Override
    public void display(int startTime, int timeToDisplay, int absoluteTimeLeft) {

    }

    @Override
    public void display(int time) {

    }

    @Override
    public void onTimeOut() {

    }

    @Override
    public void setTask(T task) {

    }

    @Override
    public void reset() {

    }

    @Override
    public Type getType() {
        return new TypeToken<T>(){}.getType();
    }
}
