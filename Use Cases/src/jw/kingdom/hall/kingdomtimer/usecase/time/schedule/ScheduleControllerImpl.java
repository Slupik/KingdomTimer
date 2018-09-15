package jw.kingdom.hall.kingdomtimer.usecase.time.schedule;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.usecase.mapper.MapperPojoToTask;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.time.listener.TimeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class ScheduleControllerImpl extends ScheduleLogic {

    private final List<TimeListener> listeners = new ArrayList<>();

    @Override
    public void addListener(TimeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(TimeListener listener) {
        listeners.remove(listener);
    }

    @Override
    protected void onListChange(List<Task> schedule) {
        List<TaskPOJO> pojo = new MapperPojoToTask().reverseMap(schedule);
        for(TimeListener listener:listeners) {
            if(listener.getType() instanceof Task) {
                listener.onScheduleChange(schedule);
            } else {
                listener.onScheduleChange(pojo);
            }
        }
    }
}
