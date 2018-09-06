/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.task;

import jw.kingdom.hall.kingdomtimer.model.observable.field.ObservableField;

@SuppressWarnings("WeakerAccess")
public class TaskBean implements ObservableTask {
    private final ObservableField<String> name = new ObservableField<>("");
    private final ObservableField<Integer> seconds = new ObservableField<>(0);
    private final ObservableField<Boolean> directDown = new ObservableField<>(true);
    private final ObservableField<TaskType> type = new ObservableField<>(TaskType.UNKNOWN);
    private final ObservableField<Boolean> studentTalk = new ObservableField<>(false);

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
    public ObservableField<Integer> secondsProperty() {
        return seconds;
    }

    @Override
    public int getSeconds() {
        return seconds.getValue();
    }

    @Override
    public void setSeconds(int seconds) {
        this.seconds.setValue(seconds);
    }

    @Override
    public ObservableField<Boolean> directDownProperty() {
        return directDown;
    }

    @Override
    public boolean isDirectDown() {
        return directDown.getValue();
    }

    @Override
    public void setDirectDown(boolean directDown) {
        this.directDown.setValue(directDown);
    }

    @Override
    public ObservableField<Boolean> studentTalkProperty() {
        return studentTalk;
    }

    @Override
    public boolean isStudentTalk() {
        return studentTalk.getValue();
    }

    @Override
    public void setStudentTalk(boolean studentTalk) {
        this.studentTalk.setValue(studentTalk);
    }

    @Override
    public ObservableField<TaskType> typeProperty() {
        return type;
    }

    @Override
    public TaskType getType() {
        return type.getValue();
    }

    @Override
    public void setType(TaskType type) {
        this.type.setValue(type);
    }

}
