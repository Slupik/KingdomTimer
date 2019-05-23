package jw.kingdom.hall.kingdomtimer.domain.time;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface TimeController {
    //Modify the schedule
    void addTask(TaskBean... tasks);
    void removeTask(TaskBean... tasks);
    void removeTask(int index);

    void clear();
    void setList(List<TaskBean> list);
    List<TaskBean> getList();

    //Controlling the time
    void startNext();
    void start(TaskBean task);
    void stop();
    void pause();
    void resume();
    TaskBean getActualTask();

    void addTime(int time);
    int getAddedTime();

    int getTime();
    void enforceTime(int time);

    //Listeners
    void addDisplay(TimeDisplay display);
    void removeDisplay(TimeDisplay display);
    void addListener(TimeListener listener);
    void removeListener(TimeListener listener);
}
