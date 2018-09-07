/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.entity.task;

public interface Task {
    String getID();

    String getName();
    void setName(String name);

    int getSeconds();
    void setSeconds(int seconds);

    boolean isDirectDown();
    void setDirectDown(boolean directDown);

    boolean isStudentTalk();
    void setStudentTalk(boolean studentTalk);

    boolean isUseBuzzer();
    void setUseBuzzer(boolean useBuzzer);

    TaskType getType();
    void setType(TaskType type);
}
