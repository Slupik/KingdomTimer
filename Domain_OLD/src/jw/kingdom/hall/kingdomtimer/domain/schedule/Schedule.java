package jw.kingdom.hall.kingdomtimer.domain.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface Schedule {
    void addListener(ScheduleListener listener);
    void removeListener(ScheduleListener listener);

    TaskBean bringOutFirstTask() throws NotEnoughTasksException;
    TaskBean bringOutTask(int index) throws NotEnoughTasksException;
    void addTask(TaskBean task);
    void addTask(List<TaskBean> task);
    void addTask(TaskBean... task);
    void removeTask(TaskBean task);
    void removeTask(int index);
    void clear();
    void moveElement(int elementIndex, int destIndex);
    List<TaskBean> getList();
    void setList(List<TaskBean> list);
}
