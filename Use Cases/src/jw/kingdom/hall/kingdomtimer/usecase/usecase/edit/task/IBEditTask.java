package jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.task;

import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.InputBoundary;

/**
 * All rights reserved & copyright ©
 */
public interface IBEditTask extends InputBoundary<OBEditTask> {
    void addOutput(OBEditTask output);
    void removeOutput(OBEditTask output);
    void updateTask(TaskPOJO pojo);
}
