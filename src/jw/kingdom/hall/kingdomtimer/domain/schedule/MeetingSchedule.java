package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.data.PredefinedTaskList;
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
    private final List<Listener> listeners = new ArrayList<>();

    public void setTasksOnline(boolean circuit) {
        new Thread(() -> {
            lastTask = null;
            PredefinedTaskList.Callback callback = list -> {
                MeetingSchedule.getInstance().clear();
                MeetingSchedule.getInstance().addTask(list);
            };
            if(isWeekend()) {
                PredefinedTaskList.getWeekendTasks(circuit, callback);
            } else {
                PredefinedTaskList.getWeekTasks(circuit, callback);
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
            for(Listener listener: listeners) {
                listener.onMeetingStart();
            }
        }
        if(list.size()==0) {
            for(Listener listener: listeners) {
                listener.onMeetingListEnd();
            }
        }
        for(Listener listener: listeners) {
            listener.onNextTask(index, task);
        }
    }

    @Override
    protected void notifyAboutAddTask(MeetingTask task) {
        for(Listener listener: listeners) {
            listener.onInsert(task);
        }
    }

    @Override
    protected void notifyAboutAddTask(MeetingTask... task) {
        for(Listener listener: listeners) {
            listener.onBulkInsert(task);
        }
    }

    @Override
    protected void notifyAboutRemoveTask(MeetingTask task) {
        for(Listener listener: listeners) {
            listener.onRemove(task);
        }
    }

    @Override
    protected void notifyAboutClear() {
        for(Listener listener: listeners) {
            listener.onClear();
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public interface Listener {
        void onMeetingStart();
        void onNextTask(int index, MeetingTask task);
        void onMeetingListEnd();
        void onMeetingEnd();
        void onRemove(MeetingTask task);
        void onInsert(MeetingTask task);
        void onBulkInsert(MeetingTask... task);
        void onClear();
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
                    for(Listener listener: listeners) {
                        listener.onMeetingEnd();
                    }
                }
            }
        });
    }
}
