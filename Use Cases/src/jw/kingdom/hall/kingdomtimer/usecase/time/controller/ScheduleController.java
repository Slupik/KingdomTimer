package jw.kingdom.hall.kingdomtimer.usecase.time.controller;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface ScheduleController {
    void clear();
    void setTasks(List<Task> list);
    void addTask(Task task);
    void removeTask(String id);
    List<Task> getTasks();

    void moveTask(String id, int index);

    void addListener(TimeListener listener);
    void removeListener(TimeListener listener);

    Task bringOutFirstTask();
}
