package jw.kingdom.hall.kingdomtimer.entity.time.countdown;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;

import static jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownState.*;

/**
 * All rights reserved & copyright ©
 */
public abstract class CountdownListener implements CountdownController.Listener {

    @Override
    public void onTaskStart(Task task) {

    }

    @Override
    public void onTimeChange(int time) {

    }

    @Override
    public void onStateChanged(CountdownState last, CountdownState now) {
        if(now==COUNTDOWNING) {
            if(last==STOP || last==null) {
                onStart();
            } else if(last==PAUSE) {
                onResume();
            }
        } else if(now==STOP) {
            onStop();
        } else if(now==PAUSE && last==COUNTDOWNING){
            onResume();
        }
        onUnknownState();
    }

    protected void onStart(){

    }

    protected void onPause(){

    }

    protected void onResume(){

    }

    protected void onStop(){

    }

    protected void onUnknownState(){

    }

    @Override
    public void onTimeOut() {

    }

    @Override
    public void onAddedTimeChange(int addedSeconds) {

    }

    @Override
    public void onEnforceTime(int enforcedTime) {

    }
}
