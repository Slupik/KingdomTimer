package jw.kingdom.hall.kingdomtimer.domain.countdown;

import javafx.beans.value.ChangeListener;
import jw.kingdom.hall.kingdomtimer.device.sound.Buzzer;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.TimeDisplayController;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

public class TimerCountdown extends TimerCountdownBase implements Countdown {
    private List<CountdownListener> listeners = new ArrayList<>();
    private MeetingTask task;
    private int addedTime = 0;
    private int startTime = 0;
    private final ChangeListener<Boolean> buzzerConditionListener = (observable, oldValue, newValue) -> setVolumeUp(newValue);

    @Override
    public void start(@NotNull MeetingTask task) {
        startTime = task.getTimeInSeconds();
        addedTime = 0;
        setTask(task);
        super.startTime(task.getTimeInSeconds());
        for(CountdownListener listener:listeners) {
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
            for(CountdownListener listener:listeners) {
                listener.onTimeOut();
            }
        }
    }

    @Override
    public int getAddedTime() {
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

    @Override
    public void stop() {
        super.stop();
        setTask(null);
        setTime(0);
        for(CountdownListener listener:listeners) {
            listener.onStop();
        }
    }

    @Override
    public void pause() {
        super.setPause(true);
        for(CountdownListener listener:listeners) {
            listener.onPause();
        }
    }

    private void setVolumeUp(Boolean value) {
        for(CountdownListener listener:listeners) {
            listener.onVolumeChange(value);
        }
    }

    @Override
    public void resume() {
        startTime(getTime());
        for(CountdownListener listener:listeners) {
            listener.onResume();
        }
    }

    @Override
    public void addTime(int time) {
        setTime(getTime()+time);
        addedTime += time;
        for(CountdownListener listener:listeners) {
            listener.onTimeManipulate(addedTime, time);
        }
    }

    @Override
    public void removeTime(int time) {
        setTime(getTime()-time);
        addedTime -= time;
        for(CountdownListener listener:listeners) {
            listener.onTimeManipulate(addedTime, time);
        }
    }

    @Override
    public void enforceTime(int time) {
        addedTime=0;
        setTime(time);
        for(CountdownListener listener:listeners) {
            listener.onEnforceTime(time);
        }
    }

    @Override
    public MeetingTask getTask() {
        return task;
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
    public void removeController(TimeDisplayController controller) {
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
    @Override
    public void addListener(CountdownListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(CountdownListener listener) {
        for(CountdownListener item:listeners) {
            if(item.getID().equals(listener.getID())) {
                listeners.remove(item);
            }
        }
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
