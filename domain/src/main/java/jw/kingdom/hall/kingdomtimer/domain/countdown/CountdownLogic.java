package jw.kingdom.hall.kingdomtimer.domain.countdown;

/**
 * All rights reserved & copyright ©
 */
public abstract class CountdownLogic implements Countdown {
    protected boolean start = false;
    protected boolean pause = false;

    private int startTime = 0;
    private int time = 0;
    private int addedTime = 0;

    private CountdownThread countdownThread;

    protected void start(int startTime) {
        pause = false;
        start = true;

        addedTime = 0;
        this.startTime = startTime;
        time = startTime;

        eventStart(startTime);
        setTimeAndNotify(time);
        reloadThread();
    }

    protected abstract void eventStart(int time);

    private void reloadThread() {
        if(countdownThread !=null) {
            countdownThread.stopCountdown();
        }
        countdownThread = getThread();
        countdownThread.start();
    }

    private CountdownThread getThread() {
        return new CountdownThread(()->{
            if(start && !pause) {
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
        pause = false;
        start = false;
        eventStop();
    }

    protected abstract void eventStop();

    @Override
    public void pause() {
        pause = true;
        start = true;
        eventPause();
    }

    protected abstract void eventPause();

    @Override
    public void resume() {
        pause = false;
        start = true;
        eventResume();
    }

    protected abstract void eventResume();

    @Override
    public int getAddedTime() {
        return addedTime;
    }

    @Override
    public void addTime(int time) {
        if(getTask()!=null) {
            addedTime += time;
            this.time += time;
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
        eventNewTime(getMaxTime(), time);
    }

    protected abstract void eventNewTime(int maxTime, int absoluteTimeLeft);

    protected int getMaxTime() {
        return addedTime+startTime;
    }
}
