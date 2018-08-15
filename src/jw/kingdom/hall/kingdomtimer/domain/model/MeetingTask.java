package jw.kingdom.hall.kingdomtimer.domain.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jw.kingdom.hall.kingdomtimer.view.utils.Randomizer;

/**
 * All rights reserved & copyright ©
 */
public class MeetingTask {
    public final String ID = Randomizer.randomStandardString(16);
    private boolean useBuzzer = false;
    private StringProperty name = new SimpleStringProperty("???");
    private int timeInSeconds = 0;

    public boolean isUseBuzzer() {
        return useBuzzer;
    }

    public void setUseBuzzer(boolean useBuzzer) {
        this.useBuzzer = useBuzzer;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

}
