package jw.kingdom.hall.kingdomtimer.domain.countdown;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public abstract class CountdownListenerProxy implements CountdownListener {

    @Override
    public void onStart(TaskBean task) {

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
    public void onTimeOut() {

    }

    @Override
    public void onTimeManipulate(int totalAdded, int added) {

    }

    @Override
    public void onEnforceTime(int time) {

    }
}
