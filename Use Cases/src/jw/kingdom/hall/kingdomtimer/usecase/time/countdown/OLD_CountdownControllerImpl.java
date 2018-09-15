package jw.kingdom.hall.kingdomtimer.usecase.time.countdown;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * All rights reserved & copyright ©
 */
public class OLD_CountdownControllerImpl implements CountdownController {
    private List<CountdownController.Listener> listeners = new ArrayList<>();
    private List<TimeDisplay> timeDisplays = new ArrayList<>();
    private CountdownState state = CountdownState.STOP;
    private Task task = null;
    private int startTime = 0;
    private int time = 0;
    private int addedTime = 0;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private OLD_CountdownThread countdownThread;

    @Override
    public void start(Task task) {
        this.task = task;
        changeState(CountdownState.COUNTDOWNING);
        addedTime = 0;
        if(task!=null) {
            startTime = task.getSeconds();
            time = task.getSeconds();
        } else {
            startTime = 0;
            time = 0;
        }
        for(TimeDisplay display:timeDisplays) {
            display.onTaskChange(task);
        }
        notifyOnTimeChange();
        executor.execute(()->{
            for(Listener listener:listeners) {
                listener.onTaskStart(task);
            }
        });
        reloadThread();
    }

    private void reloadThread() {
        if(countdownThread !=null) {
            countdownThread.stopCountdown();
        }
        countdownThread = getThread();
        countdownThread.start();
    }

    private OLD_CountdownThread getThread() {
        return new OLD_CountdownThread(()->{
            if(state == CountdownState.COUNTDOWNING) {
                int now = time;
                now--;
                setTimeAndNotify(now);
                if(now==0) {
                    executor.execute(()->{
                        for(Listener listener:listeners) {
                            listener.onTimeOut();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void stop() {
        changeState(CountdownState.STOP);
        for(TimeDisplay display:timeDisplays) {
            display.reset();
        }
    }

    @Override
    public void pause() {
        changeState(CountdownState.PAUSE);
    }

    @Override
    public void resume() {
        changeState(CountdownState.COUNTDOWNING);
    }

    private void changeState(CountdownState now) {
        CountdownState last = state;
        state = now;
        executor.execute(()->{
            for(Listener listener:listeners) {
                listener.onStateChanged(last, now);
            }
        });
    }

    @Override
    public CountdownState getState() {
        return state;
    }

    @Override
    public Task getActualTask() {
        return this.task;
    }

    @Override
    public int getAddedTime() {
        return addedTime;
    }

    @Override
    public void addTime(int time) {
        changeAddedTimeAndNotify(time);
    }

    @Override
    public void subtractTime(int time) {
        changeAddedTimeAndNotify(-time);
    }

    private void changeAddedTimeAndNotify(int timeChange) {
        addedTime += timeChange;
        executor.execute(()->{
            for(Listener listener:listeners) {
                listener.onAddedTimeChange(addedTime);
            }
        });
        setTimeAndNotify(this.time+timeChange);
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void enforceTime(int time) {
        addedTime=0;
        executor.execute(()->{
            for(Listener listener:listeners) {
                listener.onEnforceTime(time);
            }
        });
        setTimeAndNotify(time);
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void addTimeDisplay(TimeDisplay timeDisplay) {
        timeDisplays.add(timeDisplay);
    }

    @Override
    public void removeTimeDisplay(TimeDisplay timeDisplay) {
        timeDisplays.remove(timeDisplay);
    }

    private void setTimeAndNotify(int time) {
        this.time = time;
        notifyOnTimeChange();
    }

    private void notifyOnTimeChange() {
        for(TimeDisplay display:timeDisplays) {
            display.display(getMaxTime(), getCalculatedTimeToDisplay(), time);
        }
        executor.execute(()->{
            for(Listener listener:listeners) {
                listener.onTimeChange(time);
            }
        });
    }

    private int getMaxTime() {
        return addedTime+startTime;
    }

    private int getCalculatedTimeToDisplay() {
        if(task == null || task.isDirectDown()) {
            return time;
        } else {
            if(time<0){
                return time;
            } else {
                return addedTime+startTime-time;
            }
        }
    }
}
