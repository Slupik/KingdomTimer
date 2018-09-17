package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.schedule.PredefinedTaskList;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class MeetingSchedule extends MeetingScheduleBase {
    private final List<ScheduleListener> listeners = new ArrayList<>();

    @Override
    public void setTasksOnline(Config config, boolean circuit) {
        new Thread(() -> {
            lastTask = null;
            PredefinedTaskList.Callback callback = list -> {
                clear();
                addTask(list);
            };
            if(isWeekend()) {
                PredefinedTaskList.getWeekendTasks(circuit, callback);
            } else {
                PredefinedTaskList.getWeekTasks(config, circuit, callback);
            }
        }).start();
    }

    private boolean isWeekend() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return (cl.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                cl.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    @Override
    protected void notifyAboutAddTask(MeetingTask task) {
        for(ScheduleListener listener: listeners) {
            listener.onInsert(task);
        }
    }

    @Override
    protected void notifyAboutAddTask(MeetingTask... task) {
        for(ScheduleListener listener: listeners) {
            listener.onBulkInsert(task);
        }
    }

    @Override
    protected void notifyAboutRemoveTask(MeetingTask task) {
        for(ScheduleListener listener: listeners) {
            listener.onRemove(task);
        }
    }

    @Override
    protected void notifyAboutClear() {
        for(ScheduleListener listener: listeners) {
            listener.onClear();
        }
    }

    @Override
    protected void notifyAboutReset(List<MeetingTask> list) {
        for(ScheduleListener listener: listeners) {
            listener.onReset(list);
        }
    }

    @Override
    public void addListener(ScheduleListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ScheduleListener listener) {
        listeners.remove(listener);
    }
}
