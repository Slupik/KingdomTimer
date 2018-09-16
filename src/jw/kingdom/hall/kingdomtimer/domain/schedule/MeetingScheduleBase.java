package jw.kingdom.hall.kingdomtimer.domain.schedule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
abstract class MeetingScheduleBase implements Schedule {

    protected final ObservableList<MeetingTask> list = FXCollections.observableArrayList();
    protected MeetingTask lastTask = null;

    @Override
    public MeetingTask bringOutFirstTask() throws NotEnoughTasksException {
        return bringOutTask(0);
    }

    @Override
    public MeetingTask bringOutTask(int index) throws NotEnoughTasksException {
        if(list.size()<=index) {
            throw new NotEnoughTasksException();
        }
        MeetingTask task = list.get(index);
        removeTask(task);
        notifyAboutNextTask(index, task);
        return task;
    }

    @Override
    public void addTask(MeetingTask task) {
        list.add(task);
        notifyAboutAddTask(task);
    }

    @Override
    public void addTask(List<MeetingTask> task) {
        addTask(task.toArray(new MeetingTask[0]));
    }


    @Override
    public void addTask(MeetingTask... task) {
        list.addAll(task);
        notifyAboutAddTask(task);
    }

    public void removeTask(MeetingTask task) {
        list.remove(task);
        notifyAboutRemoveTask(task);
    }

    @Override
    public void removeTask(int index) {
        MeetingTask toRemove = list.get(index);
        list.remove(index);
        notifyAboutRemoveTask(toRemove);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public ObservableList<MeetingTask> getList(){
        return list;
    }

    protected abstract void notifyAboutNextTask(int index, MeetingTask task);
    protected abstract void notifyAboutAddTask(MeetingTask task);
    protected abstract void notifyAboutAddTask(MeetingTask... task);
    protected abstract void notifyAboutRemoveTask(MeetingTask task);
    protected abstract void notifyAboutClear();
}
