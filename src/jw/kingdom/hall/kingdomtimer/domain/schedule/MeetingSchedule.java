package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class MeetingSchedule extends MeetingScheduleBase {
    private final List<Listener> listeners = new ArrayList<>();

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
