package jw.kingdom.hall.kingdomtimer.domain.countdown;

import javafx.beans.value.ChangeListener;
import jw.kingdom.hall.kingdomtimer.device.sound.Buzzer;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */

public class TimerCountdown extends TimerCountdownBase {
    private List<Listener> listeners = new ArrayList<>();
    private MeetingTask task;
    private int addedTime = 0;
    private int startTime = 0;
    private final ChangeListener<Boolean> buzzerConditionListener = (observable, oldValue, newValue) -> setVolumeUp(newValue);

    public void start(@NotNull MeetingTask task) {
        startTime = task.getTimeInSeconds();
        addedTime = 0;
        setTask(task);
        super.startTime(task.getTimeInSeconds());
        for(Listener listener:listeners) {
            listener.onStart(task);
        }
    }

    @Override
    protected void onTimeChange(int time) {
        if(null != task && task.isUseBuzzer()) {
            if(time <= 0 && (Math.abs(time)%10)==0) {
                Buzzer.play();
            }
        }
        if(0 == time) {
            for(Listener listener:listeners) {
                listener.onTimeOut();
            }
        }
    }

    @Override
    protected int getAddedTime() {
        return addedTime;
    }

    @Override
    protected int getStartTime() {
        return startTime;
    }

    @Override
    protected boolean isDirectDown() {
        if(null != task) {
            return task.isCountdownDown();
        } else {
            return true;
        }
    }

    public void stop() {
        super.stop();
        setTask(null);
        setTime(0);
        for(Listener listener:listeners) {
            listener.onStop();
        }
    }

    public void pause() {
        super.setPause(true);
        for(Listener listener:listeners) {
            listener.onPause();
        }
    }

    private void setVolumeUp(Boolean value) {
        for(Listener listener:listeners) {
            listener.onVolumeChange(value);
        }
    }

    public void resume() {
        startTime(getTime());
    }

    public void addTime(int time) {
        setTime(getTime()+time);
        addedTime += time;
    }

    public void removeTime(int time) {
        setTime(getTime()-time);
        addedTime -= time;
    }

    /*
    CLASS BACKGROUND STUFF
     */
    private void setTask(MeetingTask task) {
        MeetingTask oldTask = this.task;
        if(null!=oldTask) {
            oldTask.useBuzzerProperty().removeListener(buzzerConditionListener);
        }
        this.task = task;
        if(null != task) {
            task.useBuzzerProperty().addListener(buzzerConditionListener);
        }
    }

    /*
    CONTROLLER MANAGEMENT
     */
    @Override
    public void addController(TimeDisplayController controller) {
        super.addController(controller);
    }

    @Override
    protected void removeController(TimeDisplayController controller) {
        super.removeController(controller);
    }

    /*
    GETTERS
     */
    @Override
    public boolean isPause() {
        return super.isPause();
    }

    @Override
    public boolean isStop() {
        return super.isStop();
    }

    @Override
    public int getTime() {
        return super.getTime();
    }

    /*
    LISTENERS
     */
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        for(Listener item:listeners) {
            if(item.getID().equals(listener.getID())) {
                listeners.remove(item);
            }
        }
    }
    public interface Listener {
        void onStart(MeetingTask task);
        void onPause();
        void onStop();
        void onTimeOut();
        void onVolumeChange(boolean isVolumeUp);
        String getID();
    }

    /*
    SINGLETON
     */
    private static TimerCountdown instance;
    private TimerCountdown(){
        reloadThread();
    }
    public static TimerCountdown getInstance(){
        if(instance==null) {
            instance = new TimerCountdown();
        }
        return instance;
    }
}
