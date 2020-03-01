package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
abstract class MeetingScheduleBase implements Schedule {

    protected final List<TaskBean> list = new ArrayList<>();

    @Override
    public TaskBean bringOutFirstTask() throws NotEnoughTasksException {
        return bringOutTask(0);
    }

    @Override
    public TaskBean bringOutTask(int index) throws NotEnoughTasksException {
        if(list.size()<=index) {
            throw new NotEnoughTasksException();
        }
        TaskBean task = list.get(index);
        removeTask(task);
        return task;
    }

    @Override
    public void addTask(TaskBean task) {
        list.add(task);
        notifyAboutAddTask(task);
    }

    @Override
    public void addTask(List<TaskBean> task) {
        addTask(task.toArray(new TaskBean[0]));
    }


    @Override
    public void addTask(TaskBean... task) {
        list.addAll(Arrays.asList(task));
        notifyAboutAddTask(task);
    }

    public void removeTask(TaskBean task) {
        list.remove(task);
        notifyAboutRemoveTask(task);
    }

    @Override
    public void removeTask(int index) {
        TaskBean toRemove = list.get(index);
        list.remove(index);
        notifyAboutRemoveTask(index, toRemove);
    }

    @Override
    public void clear() {
        list.clear();
        notifyAboutClear();
    }

    @Override
    public void setList(List<TaskBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyAboutReset(this.list);
    }

    public void moveElement(int elementIndex, int destIndex) {
        TaskBean task = this.list.get(elementIndex);
        this.list.remove(elementIndex);
        this.list.add(destIndex, task);
        notifyAboutMove(elementIndex, destIndex);
    }

    @Override
    public List<TaskBean> getList(){
        return list;
    }

    protected abstract void notifyAboutAddTask(TaskBean task);
    protected abstract void notifyAboutAddTask(TaskBean... task);
    protected abstract void notifyAboutRemoveTask(TaskBean task);
    protected abstract void notifyAboutRemoveTask(int index, TaskBean removed);
    protected abstract void notifyAboutClear();
    protected abstract void notifyAboutReset(List<TaskBean> list);
    protected abstract void notifyAboutMove(int elementIndex, int destIndex);
}
