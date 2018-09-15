package jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.schedule;

import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.InputBoundary;

/**
 * All rights reserved & copyright ©
 */
public interface IBEditSchedule extends InputBoundary {
    void addTask(TaskPOJO pojo);
    void removeTask(TaskPOJO pojo);
    void loadSchedule(boolean isCircuitVisit, int timeForEvaluation);
}
