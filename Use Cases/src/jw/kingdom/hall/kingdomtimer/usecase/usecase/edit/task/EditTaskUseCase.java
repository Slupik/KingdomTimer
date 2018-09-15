package jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.task;

import jw.kingdom.hall.kingdomtimer.usecase.mapper.MapperPojoToTask;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.UseCase;

/**
 * All rights reserved & copyright ©
 */
public class EditTaskUseCase extends UseCase<OBEditTask> implements IBEditTask {

    private final ScheduleController scheduleController;

    public EditTaskUseCase(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    @Override
    public void updateTask(TaskPOJO pojo) {
        scheduleController.updateTask(new MapperPojoToTask().map(pojo));
    }
}
