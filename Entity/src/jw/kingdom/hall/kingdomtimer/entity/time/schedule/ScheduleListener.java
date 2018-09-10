package jw.kingdom.hall.kingdomtimer.entity.time.schedule;

import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class ScheduleListener implements ScheduleController.Listener {
    @Override
    public void onListChange(List<ObservableTask> list) {

    }

    @Override
    public void onFirstTaskUse() {

    }

    @Override
    public void onLastTaskUse() {

    }
}
