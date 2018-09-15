package jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.task;

import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.OutputBoundary;

/**
 * All rights reserved & copyright ©
 */
public interface OBEditTask extends OutputBoundary {
    void updateTask(TaskPOJO pojo);
}
