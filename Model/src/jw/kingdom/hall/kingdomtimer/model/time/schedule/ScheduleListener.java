package jw.kingdom.hall.kingdomtimer.model.time.schedule;

import jw.kingdom.hall.kingdomtimer.model.task.ObservableTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class ScheduleListener implements ScheduleController.Listener {
    @Override
    public void onListChange(List<ObservableTask> list) {

    }
}
