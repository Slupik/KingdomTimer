/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.meeting;

import jw.kingdom.hall.kingdomtimer.model.task.Task;

import java.util.List;

public interface Meeting {
    String getName();
    void setName(String name);

    MeetingType getType();
    void setType(MeetingType type);

    List<Task> getTaskList();
}
