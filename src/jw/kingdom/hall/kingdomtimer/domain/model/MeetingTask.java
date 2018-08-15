package jw.kingdom.hall.kingdomtimer.domain.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.javafx.custom.TimeField;
import jw.kingdom.hall.kingdomtimer.view.utils.Randomizer;

/**
 * All rights reserved & copyright ©
 */
public class MeetingTask {
    public final String ID = Randomizer.randomStandardString(16);
    private BooleanProperty useBuzzer = new SimpleBooleanProperty(false);
    private StringProperty name = new SimpleStringProperty("???");
    private StringProperty formattedTime = new SimpleStringProperty("00:00:00");
    private TimeField tfTime = new TimeField();

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

    public StringProperty formattedTimeProperty(){
        return formattedTime;
    }
}
