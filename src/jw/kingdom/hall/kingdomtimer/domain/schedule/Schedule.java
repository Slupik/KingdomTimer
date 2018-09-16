package jw.kingdom.hall.kingdomtimer.domain.schedule;

import javafx.collections.ObservableList;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface Schedule {
    void addListener(ScheduleListener listener);
    void removeListener(ScheduleListener listener);

    MeetingTask bringOutFirstTask() throws NotEnoughTasksException;
    MeetingTask bringOutTask(int index) throws NotEnoughTasksException;
    void addTask(MeetingTask task);
    void addTask(List<MeetingTask> task);
    void addTask(MeetingTask... task);
    void removeTask(MeetingTask task);
    void removeTask(int index);
    void clear();
    ObservableList<MeetingTask> getList();

    //TODO delete
    void setTasksOnline(Config config, boolean circuit);
}
