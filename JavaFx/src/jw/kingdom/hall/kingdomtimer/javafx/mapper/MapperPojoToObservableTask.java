package jw.kingdom.hall.kingdomtimer.javafx.mapper;

import jw.kingdom.hall.kingdomtimer.entity.mapper.Mapper;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;

/**
 * All rights reserved & copyright ©
 */
public class MapperPojoToObservableTask extends Mapper<TaskPOJO, ObservableTask> {
    @Override
    public ObservableTask map(TaskPOJO value) {
        if(value==null) return null;

        ObservableTask bean = new TaskBean(value.id);
        bean.setSeconds(value.timeInSeconds)
                .setStudentTalk(value.studentTalk)
                .setUseBuzzer(value.useBuzzer)
                .setDirectDown(value.directDown)
                .setName(value.name)
                .setType(getTaskFor(value.type));
        return bean;
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
    public TaskPOJO reverseMap(ObservableTask value) {
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
