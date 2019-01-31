package jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.display;

import jw.kingdom.hall.kingdomtimer.config.model.ConfigReadable;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;

/**
 * All rights reserved & copyright ©
 */
public class TotalTimeControl {

    private final TimeDisplay display;
    private final ConfigReadable config;
    private Thread countdown;
    private boolean stop = false;

    public TotalTimeControl(TimeDisplay display, TimeController timer, ConfigReadable config){
        this.display = display;
        this.config = config;
        timer.addListener(new TimeListenerProxy() {
            @Override
            public void onMeetingStart() {
                super.onMeetingStart();
                stop = false;
                if(countdown!=null) {
                    countdown.stop();
                }

                countdown = getCountdownThread();
                countdown.start();
            }

            @Override
            public void onMeetingEnd() {
                super.onMeetingEnd();
                stop = true;
            }
        });
    }

    private Thread getCountdownThread() {
        return new Thread(() -> {
            final int MAX_TIME = config.getMeetingTime();
            int time = MAX_TIME;
            while(!stop) {
                display.display(MAX_TIME, time, time);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time--;
            }
            display.reset();
        });
    }
}
