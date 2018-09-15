/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.entity.task;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.entity.utils.Randomizer;

@SuppressWarnings("WeakerAccess")
public class TaskBean implements ObservableTask {
    private final String ID;
    private final ObservableField<String> name = new ObservableField<>("");
    private final ObservableField<Integer> seconds = new ObservableField<>(0);
    private final ObservableField<Boolean> directDown = new ObservableField<>(true);
    private final ObservableField<Boolean> studentTalk = new ObservableField<>(false);
    private final ObservableField<Boolean> useBuzzer = new ObservableField<>(false);
    private final ObservableField<TaskType> type = new ObservableField<>(TaskType.UNKNOWN);

    public TaskBean(){
        ID = Randomizer.randomStandardString(10);
    }

    public TaskBean(Task task){
        ID = task.getID();
        loadTask(task);
    }

    public TaskBean(String id) {
        ID = id;
    }

    private void loadTask(Task task) {
        setUseBuzzer(task.isUseBuzzer());
        setDirectDown(task.isDirectDown());
        setSeconds(task.getSeconds());
        setType(task.getType());
        setName(task.getName());
        setStudentTalk(task.isStudentTalk());
    }

    @Override
    public ObservableField<String> nameProperty() {
        return name;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public Task setName(String name) {
        this.name.setValue(name);
        return this;
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
    public Task setSeconds(int seconds) {
        this.seconds.setValue(seconds);
        return this;
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
    public Task setDirectDown(boolean directDown) {
        this.directDown.setValue(directDown);
        return this;
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
    public Task setStudentTalk(boolean studentTalk) {
        this.studentTalk.setValue(studentTalk);
        return this;
    }

    @Override
    public ObservableField<Boolean> useBuzzerProperty() {
        return useBuzzer;
    }

    @Override
    public boolean isUseBuzzer() {
        return useBuzzer.getValue();
    }

    @Override
    public Task setUseBuzzer(boolean useBuzzer) {
        this.useBuzzer.setValue(useBuzzer);
        return this;
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
    public Task setType(TaskType type) {
        this.type.setValue(type);
        return this;
    }

}
