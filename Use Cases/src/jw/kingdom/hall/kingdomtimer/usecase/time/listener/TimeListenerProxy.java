package jw.kingdom.hall.kingdomtimer.usecase.time.listener;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class TimeListenerProxy implements TimeListener {

    @Override
    public void onStart(Task task) {

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
    public void onScheduleChange(List<Task> newList) {

    }
}
