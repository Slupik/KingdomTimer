package jw.kingdom.hall.kingdomtimer.usecase.time.countdown;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownState;

/**
 * All rights reserved & copyright ©
 */
public abstract class CountdownLogic implements CountdownController {
    private CountdownState state = CountdownState.STOP;
    private Task task = null;
    private int startTime = 0;
    private int time = 0;
    private int addedTime = 0;

    private CountdownThread countdownThread;

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
        eventStart(task);
        reloadThread();
    }

    protected abstract void eventStart(Task task);

    private void reloadThread() {
        if(countdownThread !=null) {
            countdownThread.stopCountdown();
        }
        countdownThread = getThread();
        countdownThread.start();
    }

    private CountdownThread getThread() {
        return new CountdownThread(()->{
            if(state == CountdownState.COUNTDOWNING) {
                int now = time;
                now--;
                setTimeAndNotify(now);
                if(now==0) {
                    eventTimeOut();
                }
            }
        });
    }

    protected abstract void eventTimeOut();

    @Override
    public void stop() {
        changeState(CountdownState.STOP);
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
        eventStateChange(last, now);
    }

    protected abstract void eventStateChange(CountdownState last, CountdownState now);

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public int getAddedTime() {
        return addedTime;
    }

    @Override
    public void addTime(int time) {
        if(getTask()!=null) {
            addedTime += time;
            eventTimeAdd(addedTime, time);
        }
    }

    protected abstract void eventTimeAdd(int totalAdded, int added);

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void enforceTime(int time) {
        addedTime=0;
        eventEnforceTime(time);
        setTimeAndNotify(time);
    }

    protected abstract void eventEnforceTime(int time);

    protected void setTimeAndNotify(int time) {
        this.time = time;
        eventNewTime(getMaxTime(), getCalculatedTimeToDisplay(), time);
    }

    protected abstract void eventNewTime(int startTime, int timeToDisplay, int absoluteTimeLeft);

    protected int getMaxTime() {
        return addedTime+startTime;
    }

    protected int getCalculatedTimeToDisplay() {
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
