package jw.kingdom.hall.kingdomtimer.main.schedule.provider;

import jw.kingdom.hall.kingdomtimer.data.schedule.PredefinedTaskList;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class SProvider implements ScheduleProvider {
    @Override
    public void getForToday(Data input, Callback callback) {
        PredefinedTaskList.Callback onDataReceive = list -> {
            List<ObservableTask> converted = new ArrayList<>();
            for(MeetingTask mTask:list) {
                ObservableTask task = new TaskBean();
                task.setUseBuzzer(mTask.isUseBuzzer());
                task.setDirectDown(mTask.isCountdownDown());
                task.setSeconds(mTask.getTimeInSeconds());
                task.setName(mTask.getName());
                task.setType(getConvertedType(mTask.getType()));
                converted.add(task);
            }
            callback.onDataReceive(converted);
        };
        if(isWeekend()) {
            PredefinedTaskList.getWeekendTasks(input.isCircuitVisit(), onDataReceive);
        } else {
            PredefinedTaskList.getWeekTasks(input.isCircuitVisit(), onDataReceive);
        }
    }

    private TaskType getConvertedType(MeetingTask.Type type) {
        for(TaskType value:TaskType.values()) {
            if(value.name().equalsIgnoreCase(type.name())) {
                return value;
            }
        }
        return TaskType.UNKNOWN;
    }

    private boolean isWeekend() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return (cl.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                cl.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }
}
