package jw.kingdom.hall.kingdomtimer.domain.model;

import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
//https://stackoverflow.com/questions/33452847/using-pojos-as-model-layer-in-javafx-application
public class MeetingTask {
    public final String ID;
    private String name;
    private boolean useBuzzer;
    private boolean countdownDown;
    private int time;
    private MeetingTask.Type type;
    private final PropertyChangeSupport propertySupport ;

    public MeetingTask() {
        this(Randomizer.randomStandardString(16), "???", false, true, MeetingTask.Type.UNKNOWN);
    }

    public MeetingTask(String ID) {
        this(ID, "???", false, true, MeetingTask.Type.UNKNOWN);
    }

    public MeetingTask(String ID, String name, boolean useBuzzer, boolean countdownDown, MeetingTask.Type type) {
        this.ID = ID;
        this.name = name ;
        this.useBuzzer = useBuzzer ;
        this.countdownDown = countdownDown ;
        this.type = type ;

        this.propertySupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public String getName() {
        return name;
    }

    public MeetingTask setName(String name) {
        String oldTitle = this.name;
        this.name = name;
        propertySupport.firePropertyChange("name", oldTitle, name);
        return this;
    }

    public boolean isUseBuzzer() {
        return useBuzzer;
    }

    public MeetingTask setUseBuzzer(boolean useBuzzer) {
        boolean oldUseBuzzer = this.useBuzzer;
        this.useBuzzer = useBuzzer;
        propertySupport.firePropertyChange("useBuzzer", oldUseBuzzer, useBuzzer);
        return this;
    }

    public boolean isCountdownDown() {
        return countdownDown;
    }

    public MeetingTask setCountdownDown(boolean countdownDown) {
        boolean oldCountdownDown = this.countdownDown;
        this.countdownDown = countdownDown;
        propertySupport.firePropertyChange("countdownDown", oldCountdownDown, countdownDown);
        return this;
    }

    public MeetingTask.Type getType() {
        return type;
    }

    public MeetingTask setType(MeetingTask.Type type) {
        MeetingTask.Type oldType = this.type;
        this.type = type;
        propertySupport.firePropertyChange("type", oldType, type);
        return this;
    }

    public int getTime() {
        return time;
    }

    public MeetingTask setTime(int time) {
        int oldTime = this.time;
        this.time = time;
        propertySupport.firePropertyChange("time", oldTime, time);
        return this;
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
    public abstract class PropertyName {
        public static final String NAME = "name";
        public static final String TIME = "time";
        public static final String TYPE = "type";
        public static final String USE_BUZZER = "useBuzzer";
        public static final String COUNTDOWN_DOWN = "countdownDown";

        private PropertyName(){}
    }
}
/*
if any problems with javafx will occur...
column.setCellValueFactory(cellData -> {
    try {
        return new JavaBeanStringPropertyBuilder()
            .bean(cellData.getValue())
            .name("name")
            .build();
    } catch (Exception e) { throw new RuntimeException(e); }
}
 */