package jw.kingdom.hall.kingdomtimer.domain.buzzer;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplayProxy;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;

/**
 * All rights reserved & copyright ©
 */
public class BuzzerController {

    private final BuzzerPlayer player;
    private MeetingTask currentTask;

    public BuzzerController(BuzzerPlayer player, TimeController timer) {
        this.player = player;
        currentTask = timer.getActualTask();
        timer.addListener(new TimeListenerProxy() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                currentTask = task;
            }

            @Override
            public void onStop() {
                super.onStop();
                currentTask = null;
            }
        });
        timer.addDisplay(new TimeDisplayProxy() {
            @Override
            public void display(int startTime, int timeToDisplay, int absoluteTimeLeft) {
                super.display(startTime, timeToDisplay, absoluteTimeLeft);
                onTimeChange(absoluteTimeLeft);
            }

            @Override
            public void display(int time) {
                super.display(time);
                onTimeChange(time);
            }
        });
    }

    private void onTimeChange(int time) {
        if(null != currentTask && currentTask.isUseBuzzer()) {
            if(time <= 0 && (Math.abs(time)%10)==0) {
                player.play();
            }
        }
    }
}
