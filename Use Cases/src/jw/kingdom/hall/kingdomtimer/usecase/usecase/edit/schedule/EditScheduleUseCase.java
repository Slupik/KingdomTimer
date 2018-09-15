package jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.schedule;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.usecase.mapper.MapperPojoToTask;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.time.listener.TimeListenerProxy;
import jw.kingdom.hall.kingdomtimer.usecase.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.UseCase;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class EditScheduleUseCase extends UseCase<OBEditSchedule> implements IBEditSchedule {

    private final ScheduleController schedule;

    public EditScheduleUseCase(ScheduleController schedule){
        this.schedule = schedule;
        schedule.addListener(new TimeListenerProxy<Task>() {
            @Override
            public void onScheduleChange(List<Task> newList) {
                super.onScheduleChange(newList);
                List<TaskPOJO> pojo = new MapperPojoToTask().reverseMap(newList);
                for(OBEditSchedule output:outputs) {
                    output.updateList(pojo);
                }
            }
        });
    }

    @Override
    public void addOutput(OBEditSchedule output) {
        super.addOutput(output);
        output.updateList(new MapperPojoToTask().reverseMap(schedule.getTasks()));
    }

    @Override
    public void addTask(TaskPOJO pojo) {
        schedule.addTask(new MapperPojoToTask().map(pojo));
    }

    @Override
    public void removeTask(TaskPOJO pojo) {
        schedule.removeTask(new MapperPojoToTask().map(pojo).getID());
    }

    @Override
    public void loadSchedule(boolean isCircuitVisit, int timeForEvaluation) {
        //TODO implement (loadSchedule)
    }

    @Override
    public void setSchedule(List<TaskPOJO> list) {
        schedule.setTasks(new MapperPojoToTask().map(list));
    }
}
