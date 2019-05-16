package jw.kingdom.hall.kingdomtimer.domain.clock;


/**
 * All rights reserved & copyright ©
 */
abstract class ClockBase implements Clock {

    protected static final int STOP = -1;

    private CountdownThread countdownThread;
    private int time = 0;
    private boolean stop = false;
    private static final int maxTime = 24*3600;

    @Override
    public void start(int startValue) {
        stop = false;
        time = startValue;
        reloadThread();
    }

    private void reloadThread() {
        if(countdownThread !=null) {
            countdownThread.stopCountdown();
        }
        countdownThread = getThread();
        countdownThread.start();
    }

    private CountdownThread getThread() {
        return new CountdownThread(()->{
            if(!stop) {
                time = (time+1)%maxTime;
                onTimeChange(time);
            }
        });
    }

    @Override
    public void stop() {
        stop = true;
        if(countdownThread !=null) {
            countdownThread.stopCountdown();
            countdownThread = null;
        }
        onTimeChange(STOP);
    }

    protected abstract void onTimeChange(int time);
}
