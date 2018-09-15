package jw.kingdom.hall.kingdomtimer.javafx.entity.task;

import javafx.beans.property.*;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.entity.utils.Randomizer;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;

/**
 * All rights reserved & copyright ©
 */
public class TaskFxBean implements Task {

    private final ObservableTask observableTask;
    private final Task normalTask;
    private final String ID;

    private BooleanProperty useBuzzer = new SimpleBooleanProperty(false);
    private BooleanProperty studentTalk = new SimpleBooleanProperty(false);
    private StringProperty name = new SimpleStringProperty("???");
    private BooleanProperty countdownDown = new SimpleBooleanProperty(true);
    private ObjectProperty<TaskType> type = new SimpleObjectProperty<>(TaskType.UNKNOWN);
    //Used by javafx in TableView
    private TimeField tfTime = new TimeField();

    public TaskFxBean(){
        ID = Randomizer.randomStandardString(10);
        observableTask = generateObservable();
        normalTask = observableTask;
        bindObservableTask();
    }

    public TaskFxBean(ObservableTask task) {
        ID = task.getID();
        observableTask = task;
        normalTask = task;
        loadTask(task);
        bindObservableTask();
    }

    public TaskFxBean(Task task) {
        ID = task.getID();
        normalTask = task;
        if(task instanceof ObservableTask) {
            observableTask = ((ObservableTask) task);
        } else {
            observableTask = generateObservable();
        }
        loadTask(task);
        bindObservableTask();
    }

    private boolean ignore = false;

    public TaskFxBean(String id) {
        ID = id;


        observableTask = generateObservable();
        normalTask = observableTask;
    }

    private void bindObservableTask() {
        observableTask.directDownProperty().addListener((observableObject, oldValue, newValue) -> {
            if(ignore) return;
            ignore = true;
            setDirectDown(newValue);
            ignore = false;
        });
        observableTask.nameProperty().addListener((observableObject, oldValue, newValue) -> {
            if(ignore) return;
            ignore = true;
            setName(newValue);
            ignore = false;
        });
        observableTask.secondsProperty().addListener((observableObject, oldValue, newValue) -> {
            if(ignore) return;
            ignore = true;
            setTimeInSeconds(newValue);
            ignore = false;
        });
        observableTask.typeProperty().addListener((observableObject, oldValue, newValue) -> {
            if(ignore) return;
            ignore = true;
            setType(newValue);
            ignore = false;
        });
        observableTask.useBuzzerProperty().addListener((observableObject, oldValue, newValue) -> {
            if(ignore) return;
            ignore = true;
            setUseBuzzer(newValue);
            ignore = false;
        });
    }

    private void loadTask(Task task) {
        setUseBuzzer(task.isUseBuzzer());
        setCountdownDown(task.isDirectDown());
        setTimeInSeconds(task.getSeconds());
        setType(task.getType());
        setName(task.getName());
        setStudentTalk(task.isStudentTalk());
    }

    private ObservableTask generateObservable() {
        return new TaskBean(this);
    }

    public ObservableTask getAsObservable(){
        return observableTask;
    }

    public TimeField getTfTime() {
        return tfTime;
    }

    public void setTfTime(TimeField tfTime) {
        this.tfTime = tfTime;
    }

    public BooleanProperty useBuzzerProperty() {
        return useBuzzer;
    }

    public boolean isUseBuzzer() {
        return useBuzzer.getValue();
    }

    public Task setUseBuzzer(boolean useBuzzer) {
        this.useBuzzer.setValue(useBuzzer);
        normalTask.setUseBuzzer(useBuzzer);
        return this;
    }

    public StringProperty nameProperty() {
        return this.name;
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
        ignore = true;
        normalTask.setName(name);
        return this;
    }

    public int getTimeInSeconds() {
        return tfTime.getAllSeconds();
    }

    public void setTimeInSeconds(int timeInSeconds) {
        tfTime.setSeconds(timeInSeconds);
        normalTask.setSeconds(timeInSeconds);
    }

    public BooleanProperty countdownProperty() {
        return countdownDown;
    }

    public boolean isCountdownDown() {
        return countdownDown.getValue();
    }

    public void setCountdownDown(boolean countdownDown) {
        this.countdownDown.setValue(countdownDown);
        normalTask.setDirectDown(countdownDown);
    }

    @Override
    public TaskType getType() {
        return type.get();
    }

    public ObjectProperty<TaskType> typeProperty() {
        return type;
    }

    @Override
    public Task setType(TaskType type) {
        this.type.set(type);
        normalTask.setType(type);
        return this;
    }

    @Override
    public int getSeconds() {
        return getTimeInSeconds();
    }

    @Override
    public Task setSeconds(int seconds) {
        setTimeInSeconds(seconds);
        return this;
    }

    @Override
    public boolean isDirectDown() {
        return isCountdownDown();
    }

    @Override
    public Task setDirectDown(boolean directDown) {
        setCountdownDown(directDown);
        return this;
    }

    @Override
    public boolean isStudentTalk() {
        return studentTalk.getValue();
    }

    public BooleanProperty studentTalkProperty() {
        return studentTalk;
    }

    @Override
    public Task setStudentTalk(boolean studentTalk) {
        this.studentTalk.setValue(studentTalk);
        normalTask.setStudentTalk(studentTalk);
        return this;
    }
}
