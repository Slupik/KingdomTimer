/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.entity.task;

public interface Task {
    String getID();

    String getName();
    Task setName(String name);

    int getSeconds();
    Task setSeconds(int seconds);

    boolean isDirectDown();
    Task setDirectDown(boolean directDown);

    boolean isStudentTalk();
    Task setStudentTalk(boolean studentTalk);

    boolean isUseBuzzer();
    Task setUseBuzzer(boolean useBuzzer);

    TaskType getType();
    Task setType(TaskType type);
}
