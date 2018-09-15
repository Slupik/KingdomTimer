package jw.kingdom.hall.kingdomtimer.usecase.mapper;

import jw.kingdom.hall.kingdomtimer.entity.mapper.Mapper;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;

/**
 * All rights reserved & copyright ©
 */
public class MapperPojoToTask extends Mapper<TaskPOJO, Task> {

    @Override
    public Task map(TaskPOJO value) {
        if(value==null) return null;

        return new TaskBean(value.id)
                .setSeconds(value.timeInSeconds)
                .setStudentTalk(value.studentTalk)
                .setUseBuzzer(value.useBuzzer)
                .setDirectDown(value.directDown)
                .setName(value.name)
                .setType(getTaskFor(value.type));
    }

    private TaskType getTaskFor(String name) {
        for(TaskType type:TaskType.values()) {
            if(type.name().equals(name)) {
                return type;
            }
        }
        return TaskType.UNKNOWN;
    }

    @Override
    public TaskPOJO reverseMap(Task value) {
        if(value==null) return null;

        TaskPOJO pojo = new TaskPOJO();
        pojo.id = value.getID();
        pojo.type = value.getType().name();
        pojo.directDown = value.isDirectDown();
        pojo.name = value.getName();
        pojo.studentTalk = value.isStudentTalk();
        pojo.timeInSeconds = value.getSeconds();
        pojo.useBuzzer = value.isUseBuzzer();
        return pojo;
    }
}
