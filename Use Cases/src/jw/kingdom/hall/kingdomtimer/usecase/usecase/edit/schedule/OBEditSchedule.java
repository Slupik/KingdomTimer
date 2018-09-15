package jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.schedule;

import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.OutputBoundary;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface OBEditSchedule extends OutputBoundary {
    void updateList(List<TaskPOJO> newList);
}
