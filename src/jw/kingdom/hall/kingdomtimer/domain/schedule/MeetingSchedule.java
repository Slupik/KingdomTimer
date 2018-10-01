package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class MeetingSchedule extends MeetingScheduleBase {
    private final List<ScheduleListener> listeners = new ArrayList<>();

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
    protected void notifyAboutRemoveTask(int index, MeetingTask removed) {
        for(ScheduleListener listener: listeners) {
            listener.onRemove(index, removed);
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
    protected void notifyAboutMove(int elementIndex, int destIndex) {
        for(ScheduleListener listener: listeners) {
            listener.onMove(elementIndex, destIndex);
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
