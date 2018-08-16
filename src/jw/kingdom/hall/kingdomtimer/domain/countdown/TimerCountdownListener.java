package jw.kingdom.hall.kingdomtimer.domain.countdown;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.view.utils.Randomizer;

/**
 * All rights reserved & copyright ©
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
    public void onStop() {

    }

    @Override
    public void onVolumeChange(boolean isVolumeUp) {

    }

    @Override
    public String getID() {
        return ID;
    }
}
