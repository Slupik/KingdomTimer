package jw.kingdom.hall.kingdomtimer.usecase.time.countdown;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownState;
import jw.kingdom.hall.kingdomtimer.usecase.time.listener.TimeListener;
import jw.kingdom.hall.kingdomtimer.usecase.time.display.TimeDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownState.COUNTDOWNING;
import static jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownState.PAUSE;
import static jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownState.STOP;

/**
 * All rights reserved & copyright ©
 */
public class CountdownControllerImpl extends CountdownLogic {
    private final List<TimeDisplay> displays = new ArrayList<>();
    private final List<TimeListener> listeners = new ArrayList<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void eventStart(Task task) {
        for(TimeDisplay display:displays) {
            display.setTask(task);
        }
        executor.execute(()->{
            for(TimeListener listener:listeners) {
                listener.onStart(task);
            }
        });
    }

    @Override
    protected void eventTimeOut() {
        for(TimeDisplay display:displays) {
            display.onTimeOut();
        }
    }

    @Override
    protected void eventStateChange(CountdownState last, CountdownState now) {
        if(now==COUNTDOWNING) {
            if(last==STOP || last==null) {
                onStart();
            } else if(last==PAUSE) {
                onResume();
            }
        } else if(now==STOP) {
            onStop();
        } else if(now==PAUSE && last==COUNTDOWNING){
            onPause();
        }
        onUnknownState();
    }

    private void onStop() {
        executor.execute(()->{
            for(TimeListener listener:listeners) {
                listener.onStop();
            }
        });
    }

    private void onPause() {
        executor.execute(()->{
            for(TimeListener listener:listeners) {
                listener.onPause();
            }
        });
    }

    private void onResume() {
        executor.execute(()->{
            for(TimeListener listener:listeners) {
                listener.onResume();
            }
        });
    }

    private void onUnknownState() {}
    private void onStart() {}

    @Override
    protected void eventTimeAdd(int totalAdded, int added) {
        executor.execute(()->{
            for(TimeListener listener:listeners) {
                listener.onTimeAdded(totalAdded, added);
            }
        });
    }

    @Override
    protected void eventEnforceTime(int time) {
        executor.execute(()->{
            for(TimeListener listener:listeners) {
                listener.onTimeEnforce(time);
            }
        });
    }

    @Override
    protected void eventNewTime(int startTime, int timeToDisplay, int absoluteTimeLeft) {
        for(TimeDisplay display:displays) {
            display.display(startTime, timeToDisplay, absoluteTimeLeft);
        }
    }

    @Override
    public void addDisplay(TimeDisplay timeDisplay) {
        timeDisplay.reset();
        displays.add(timeDisplay);
    }

    @Override
    public void removeDisplay(TimeDisplay timeDisplay) {
        timeDisplay.reset();
        displays.remove(timeDisplay);
    }

    @Override
    public void addListener(TimeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(TimeListener listener) {
        listeners.remove(listener);
    }
}
