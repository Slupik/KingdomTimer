package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
abstract class MeetingScheduleBase implements Schedule {

    protected final List<MeetingTask> list = new ArrayList<>();
    protected final List<List<MeetingTask>> binded = new ArrayList<>();
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
        return task;
    }

    @Override
    public void addTask(MeetingTask task) {
        list.add(task);
        for(List<MeetingTask> bindedList:binded) {
            bindedList.add(task);
        }
        notifyAboutAddTask(task);
    }

    @Override
    public void addTask(List<MeetingTask> task) {
        addTask(task.toArray(new MeetingTask[0]));
    }


    @Override
    public void addTask(MeetingTask... task) {
        list.addAll(Arrays.asList(task));
        for(List<MeetingTask> bindedList:binded) {
            bindedList.addAll(Arrays.asList(task));
        }
        notifyAboutAddTask(task);
    }

    public void removeTask(MeetingTask task) {
        list.remove(task);
        for(List<MeetingTask> bindedList:binded) {
            bindedList.remove(task);
        }
        notifyAboutRemoveTask(task);
    }

    @Override
    public void removeTask(int index) {
        MeetingTask toRemove = list.get(index);
        list.remove(index);
        for(List<MeetingTask> bindedList:binded) {
            bindedList.remove(index);
        }
        notifyAboutRemoveTask(toRemove);
    }

    @Override
    public void clear() {
        list.clear();
        for(List<MeetingTask> bindedList:binded) {
            bindedList.clear();
        }
        notifyAboutClear();
    }

    @Override
    public void setList(List<MeetingTask> list) {
        this.list.clear();
        this.list.addAll(list);
        for(List<MeetingTask> bindedList:binded) {
            bindedList.clear();
            bindedList.addAll(list);
        }
        notifyAboutReset(this.list);
    }

    //TODO bind this to tied list
    public void moveElement(int elementIndex, int destIndex) {
        MeetingTask task = this.list.get(elementIndex);
        this.list.remove(elementIndex);
        this.list.add(destIndex, task);
    }

    //TODO Create special object for binding which will be using listeners
    public void bindWriteOnly(List<MeetingTask> list) {
        binded.add(list);
    }

    public void unbindWriteOnly(List<MeetingTask> list) {
        binded.remove(list);
    }

    @Override
    public List<MeetingTask> getList(){
        return list;
    }

    protected abstract void notifyAboutAddTask(MeetingTask task);
    protected abstract void notifyAboutAddTask(MeetingTask... task);
    protected abstract void notifyAboutRemoveTask(MeetingTask task);
    protected abstract void notifyAboutClear();
    protected abstract void notifyAboutReset(List<MeetingTask> list);
}
