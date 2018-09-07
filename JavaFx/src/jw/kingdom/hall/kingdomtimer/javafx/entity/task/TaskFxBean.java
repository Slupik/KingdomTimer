package jw.kingdom.hall.kingdomtimer.javafx.entity.task;

import javafx.beans.property.*;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.entity.utils.Randomizer;

/**
 * All rights reserved & copyright ©
 */
public class TaskFxBean implements Task {

    private final ObservableTask observable;
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
        observable = generateObservable();
    }

    public TaskFxBean(ObservableTask task) {
        ID = Randomizer.randomStandardString(10);
        observable = task;
        loadTask(task);

        task.directDownProperty().addListener((observableObject, oldValue, newValue) -> setDirectDown(newValue));
        task.nameProperty().addListener((observableObject, oldValue, newValue) -> setName(newValue));
        task.secondsProperty().addListener((observableObject, oldValue, newValue) -> setTimeInSeconds(newValue));
        task.typeProperty().addListener((observableObject, oldValue, newValue) -> setType(newValue));
        task.useBuzzerProperty().addListener((observableObject, oldValue, newValue) -> setUseBuzzer(newValue));
    }

    public TaskFxBean(Task task) {
        ID = task.getID();
        observable = generateObservable();

        loadTask(task);
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
        ObservableTask task = new TaskBean();
        return task;
    }

    public ObservableTask getAsObservable(){
        return observable;
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

    public void setUseBuzzer(boolean useBuzzer) {
        this.useBuzzer.setValue(useBuzzer);
        observable.setUseBuzzer(useBuzzer);
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
    public void setName(String name) {
        this.name.setValue(name);
        observable.setName(name);
    }

    public int getTimeInSeconds() {
        return tfTime.getAllSeconds();
    }

    public void setTimeInSeconds(int timeInSeconds) {
        tfTime.setSeconds(timeInSeconds);
        observable.setSeconds(timeInSeconds);
    }

    public BooleanProperty countdownProperty() {
        return countdownDown;
    }

    public boolean isCountdownDown() {
        return countdownDown.getValue();
    }

    public void setCountdownDown(boolean countdownDown) {
        this.countdownDown.setValue(countdownDown);
        observable.setDirectDown(countdownDown);
    }

    @Override
    public TaskType getType() {
        return type.get();
    }

    public ObjectProperty<TaskType> typeProperty() {
        return type;
    }

    @Override
    public void setType(TaskType type) {
        this.type.set(type);
        observable.setType(type);
    }

    @Override
    public int getSeconds() {
        return getTimeInSeconds();
    }

    @Override
    public void setSeconds(int seconds) {
        setTimeInSeconds(seconds);
    }

    @Override
    public boolean isDirectDown() {
        return isCountdownDown();
    }

    @Override
    public void setDirectDown(boolean directDown) {
        setCountdownDown(directDown);
    }

    @Override
    public boolean isStudentTalk() {
        return studentTalk.getValue();
    }

    public BooleanProperty studentTalkProperty() {
        return studentTalk;
    }

    @Override
    public void setStudentTalk(boolean studentTalk) {
        this.studentTalk.setValue(studentTalk);
        observable.setStudentTalk(studentTalk);
    }
}
