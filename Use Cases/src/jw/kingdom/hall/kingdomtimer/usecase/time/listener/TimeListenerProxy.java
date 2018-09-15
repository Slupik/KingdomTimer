package jw.kingdom.hall.kingdomtimer.usecase.time.listener;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class TimeListenerProxy<T> implements TimeListener<T> {

    @Override
    public void onStart(T task) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onTimeEnforce(int enforced) {

    }

    @Override
    public void onTimeAdded(int totalAdded, int added) {

    }

    @Override
    public void onMeetingStart() {

    }

    @Override
    public void onMeetingEnd() {

    }

    @Override
    public void onScheduleChange(List<T> newList) {

    }

    @Override
    public Type getType() {
        return new TypeToken<T>(){}.getType();
    }
}
