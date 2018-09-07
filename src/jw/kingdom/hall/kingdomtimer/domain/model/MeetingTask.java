package jw.kingdom.hall.kingdomtimer.domain.model;

import javafx.beans.property.*;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.model.utils.Randomizer;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class MeetingTask {
    public final String ID;
    private BooleanProperty useBuzzer = new SimpleBooleanProperty(false);
    private StringProperty name = new SimpleStringProperty("???");
    private BooleanProperty countdownDown = new SimpleBooleanProperty(true);
    private ObjectProperty<Type> type = new SimpleObjectProperty<>(Type.UNKNOWN);
    //Used by javafx in TableView
    private TimeField tfTime = new TimeField();

    public MeetingTask(){
        this(Randomizer.randomStandardString(16));
    }

    public MeetingTask(String ID) {
        this.ID = ID;
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
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public int getTimeInSeconds() {
        return tfTime.getAllSeconds();
    }

    public void setTimeInSeconds(int timeInSeconds) {
        tfTime.setSeconds(timeInSeconds);
    }

    public BooleanProperty countdownProperty() {
        return countdownDown;
    }

    public boolean isCountdownDown() {
        return countdownDown.getValue();
    }

    public void setCountdownDown(boolean countdownDown) {
        this.countdownDown.setValue(countdownDown);
    }

    public Type getType() {
        return type.get();
    }

    public ObjectProperty<Type> typeProperty() {
        return type;
    }

    public void setType(Type type) {
        this.type.set(type);
    }

    public enum Type {
        UNKNOWN,
        NONE,
        TREASURES,
        MINISTRY,
        LIVING,
        WATCHTOWER,
        LECTURE,
        CIRCUIT,
        OTHER
    }
}
