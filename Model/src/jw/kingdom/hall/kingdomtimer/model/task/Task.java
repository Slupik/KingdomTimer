/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.task;

public interface Task {
    String getName();
    void setName(String name);

    int getSeconds();
    void setSeconds(int seconds);

    boolean getDirectDown();
    void setDirectDown(boolean directDown);

    TaskType getType();
    void setType(TaskType type);
}
