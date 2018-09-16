package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.schedule.PredefinedTaskList;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
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

    protected void notifyAboutNextTask(int index, MeetingTask task) {
        boolean isFirstTask = null == lastTask;
        lastTask = task;//setting this after notify listeners may be too slow
        if(isFirstTask) {
            for(ScheduleListener listener: listeners) {
                listener.onMeetingStart();
            }
        }
        if(list.size()==0) {
            for(ScheduleListener listener: listeners) {
                listener.onMeetingListEnd();
            }
        }
        for(ScheduleListener listener: listeners) {
            listener.onNextTask(index, task);
        }
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
    public void addListener(ScheduleListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ScheduleListener listener) {
        listeners.remove(listener);
    }

    /*
    Singleton
     */
    private static MeetingSchedule schedule;
    public static MeetingSchedule getInstance(){
        if(null == schedule) {
            schedule = new MeetingSchedule();
        }
        return schedule;
    }
    private MeetingSchedule(){
        TimerCountdown.getInstance().addListener(new TimerCountdownListener() {
            @Override
            public void onStop() {
                super.onStop();
                if(list.size()==0) {
                    for(ScheduleListener listener: listeners) {
                        listener.onMeetingEnd();
                    }
                }
            }
        });
    }
}
