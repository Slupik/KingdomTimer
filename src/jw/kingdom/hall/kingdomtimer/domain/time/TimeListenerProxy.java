package jw.kingdom.hall.kingdomtimer.domain.time;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class TimeListenerProxy implements TimeListener {

    @Override
    public void onStart(MeetingTask task) {

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
    public void onScheduleChange(List<MeetingTask> newList) {

    }
}
