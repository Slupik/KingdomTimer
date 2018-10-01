package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.timedirect;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.beans.PropertyChangeListener;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForObj extends BtnTimeDirectBase {
    private final Config config;
    private MeetingTask task;
    private PropertyChangeListener listener = evt -> {
        if(evt.getPropertyName().equals(MeetingTask.PropertyName.COUNTDOWN_DOWN)) {
            setDirectDown((Boolean) evt.getNewValue());
        }
    };

    public BtnTimeDirectForObj(Config config, Button button) {
        super(button);
        this.config = config;
        init();
    }

    @Override
    protected void changeDirect() {
        setDirectDown(!isDirectDown());
    }

    @Override
    public boolean isDirectDown() {
        if(null == task) {
            return getConfig().isDirectDown();
        }
        return task.isCountdownDown();
    }

    private void setDirectDown(boolean isDirectDown) {
        if(null != task) {
            task.setCountdownDown(isDirectDown);
        }
        updateImage();
        notifyCountdownDirectChange(isDirectDown);
    }

    public void loadTask(MeetingTask task) {
        if(null != this.task) {
            this.task.removePropertyChangeListener(listener);
        }
        this.task = task;
        updateImage();
        if(null != this.task) {
            this.task.addPropertyChangeListener(listener);
        }
    }

    private Config getConfig() {
        return config;
    }
}
