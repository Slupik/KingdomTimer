package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.timedirect;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForObj extends BtnTimeDirectBase {
    private final Config config;
    private MeetingTask task;
    private final ChangeListener<Boolean> countdownListener = (observable, oldValue, newValue) -> setDirectDown(newValue);

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
            this.task.countdownProperty().removeListener(countdownListener);
        }
        this.task = task;
        updateImage();
        if(null != this.task) {
            this.task.countdownProperty().addListener(countdownListener);
        }
    }

    private Config getConfig() {
        return config;
    }
}
