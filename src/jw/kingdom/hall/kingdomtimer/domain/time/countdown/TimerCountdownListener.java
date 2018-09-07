package jw.kingdom.hall.kingdomtimer.domain.time.countdown;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.entity.utils.Randomizer;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public abstract class TimerCountdownListener implements TimerCountdown.Listener {
    private final String ID = Randomizer.randomStandardString(10);

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
    public void onTimeOut() {

    }

    @Override
    public void onTimeManipulate(int totalAdded) {

    }

    @Override
    public void onEnforceTime(int time) {

    }

    @Override
    public void onVolumeChange(boolean isVolumeUp) {

    }

    @Override
    public String getID() {
        return ID;
    }
}
