package jw.kingdom.hall.kingdomtimer.usecase.time.controller;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.usecase.time.display.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.usecase.time.listener.TimeListener;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface TimeController {
    //Controlling the time
    void startTask(Task task);
    void pause();
    void resume();
    void stop();
    void startNext();
    Task getActualTask();

    void addTime(int time);
    int getAddedTime();

    int getTime();
    void enforceTime(int time);

    //Modify the schedule
    void moveTask(String id, int index);
    void addTask(Task task);
    void remove(String taskId);
    void setSchedule(List<Task> list);
    List<Task> getSchedule();

    //Listeners
    void addDisplay(TimeDisplay display);
    void removeDisplay(TimeDisplay display);
    void addListener(TimeListener listener);
    void removeListener(TimeListener listener);
}
