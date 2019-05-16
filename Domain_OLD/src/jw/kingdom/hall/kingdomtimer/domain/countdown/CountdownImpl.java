package jw.kingdom.hall.kingdomtimer.domain.countdown;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplay;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * All rights reserved & copyright ©
 */
public class CountdownImpl extends CountdownLogic {

    private TaskBean task = null;
    private final List<CountdownListener> listeners = new ArrayList<>();
    private final List<TimeDisplay> displays = new ArrayList<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void start(@NotNull TaskBean task) {
        this.task = task;
        start(task.getTime());
    }

    @Override
    public void removeTime(int time) {
        addTime(Math.negateExact(time));
    }

    @Override
    public TaskBean getTask() {
        return task;
    }

    @Override
    protected void eventStart(int time) {
        for(TimeDisplay display:displays) {
            display.setTask(task);
        }
        executor.execute(()->{
            for(CountdownListener listener:listeners) {
                listener.onStart(task);
            }
        });
    }

    @Override
    protected void eventTimeOut() {
        for(TimeDisplay display:displays) {
            display.onTimeOut();
        }
        executor.execute(()->{
            for(CountdownListener listener:listeners) {
                listener.onTimeOut();
            }
        });
    }

    @Override
    protected void eventStop() {
        for(TimeDisplay display:displays) {
            display.reset();
        }
        executor.execute(()->{
            for(CountdownListener listener:listeners) {
                listener.onStop();
            }
        });
    }

    @Override
    protected void eventPause() {
        executor.execute(()->{
            for(CountdownListener listener:listeners) {
                listener.onPause();
            }
        });
    }

    @Override
    protected void eventResume() {
        executor.execute(()->{
            for(CountdownListener listener:listeners) {
                listener.onResume();
            }
        });
    }

    @Override
    protected void eventTimeAdd(int totalAdded, int added) {
        executor.execute(()->{
            for(CountdownListener listener:listeners) {
                listener.onTimeManipulate(totalAdded, added);
            }
        });
    }

    @Override
    protected void eventEnforceTime(int time) {
        executor.execute(()->{
            for(CountdownListener listener:listeners) {
                listener.onEnforceTime(time);
            }
        });
    }

    @Override
    protected void eventNewTime(int maxTime, int absoluteTimeLeft) {
        int toDisplay = TimeCalculator.getDisplayTime(maxTime, absoluteTimeLeft, task.isCountdownDown());
        for(TimeDisplay display:displays) {
            display.display(maxTime, toDisplay, absoluteTimeLeft);
        }
    }

    @Override
    public void addDisplay(TimeDisplay display) {
        displays.add(display);
    }

    @Override
    public void removeDisplay(TimeDisplay display) {
        displays.remove(display);
    }

    @Override
    public void addListener(CountdownListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(CountdownListener listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean isPause() {
        return pause;
    }

    @Override
    public boolean isStop() {
        return !start;
    }
}
