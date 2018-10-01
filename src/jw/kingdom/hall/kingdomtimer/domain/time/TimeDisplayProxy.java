package jw.kingdom.hall.kingdomtimer.domain.time;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

/**
 * All rights reserved & copyright ©
 */
public abstract class TimeDisplayProxy implements TimeDisplay {
    @Override
    public void setTask(TaskBean task) {

    }

    @Override
    public void display(int startTime, int timeToDisplay, int absoluteTimeLeft) {

    }

    @Override
    public void display(int time) {

    }

    @Override
    public void onTimeOut() {

    }

    @Override
    public void reset() {

    }
}
