package jw.kingdom.hall.kingdomtimer.domain.time;

import javafx.collections.ObservableList;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface TimeController {
    //Modify the schedule
    void addTask(MeetingTask... tasks);
    void removeTask(MeetingTask... tasks);
    void removeTask(int index);

    void clear();
    void setList(List<MeetingTask> list);
    ObservableList<MeetingTask> getList();

    //Controlling the time
    void startNext();
    void start(MeetingTask task);
    void stop();
    void pause();
    void resume();
    MeetingTask getActualTask();

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
