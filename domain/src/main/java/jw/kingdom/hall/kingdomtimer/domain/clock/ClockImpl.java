package jw.kingdom.hall.kingdomtimer.domain.clock;

import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * All rights reserved & copyright ©
 */
public class ClockImpl extends ClockBase {

    private final List<TimeDisplay> displays = new ArrayList<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onTimeChange(int time) {
        executor.execute(()->{
            for(TimeDisplay display:displays) {
                if(display.isClock()) {
                    if(time==STOP) {
                        display.reset();
                    } else {
                        display.display(time);
                    }
                }
            }
        });
    }

    @Override
    public void addDisplay(TimeDisplay display) {
        displays.add(display);
    }

    @Override
    public void removeDisplay(TimeDisplay display) {
        displays.remove(display);
    }
}
