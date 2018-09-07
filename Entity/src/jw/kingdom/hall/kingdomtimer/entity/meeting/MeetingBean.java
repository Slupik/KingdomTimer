/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.entity.meeting;

import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.ObservableArray;

import java.util.List;

public class MeetingBean implements ObservableMeeting {
    private final ObservableField<String> name = new ObservableField<>("");
    private final ObservableField<MeetingType> type = new ObservableField<>(MeetingType.UNKNOWN);
    private final TaskList tasks = new TaskList();

    @Override
    public ObservableField<String> nameProperty() {
        return name;
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public void setName(String name) {
        this.name.setValue(name);
    }

    @Override
    public ObservableField<MeetingType> typeProperty() {
        return type;
    }

    @Override
    public MeetingType getType() {
        return type.getValue();
    }

    @Override
    public void setType(MeetingType type) {
        this.type.setValue(type);
    }

    @Override
    public List<Task> getTaskList() {
        return tasks.getTasksList();
    }

    @Override
    public ObservableArray<ObservableTask> tasksProperty() {
        return tasks;
    }

}
