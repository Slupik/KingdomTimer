/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.task;

public interface Task {
    String getName();
    void setName(String name);

    int getSeconds();
    void setSeconds(int seconds);

    boolean isDirectDown();
    void setDirectDown(boolean directDown);

    boolean isStudentTalk();
    void setStudentTalk(boolean studentTalk);

    TaskType getType();
    void setType(TaskType type);
}
