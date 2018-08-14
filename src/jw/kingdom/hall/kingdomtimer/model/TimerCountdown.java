package jw.kingdom.hall.kingdomtimer.model;

import jw.kingdom.hall.kingdomtimer.view.common.controller.TimeDisplayController;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */

//TODO add buzzer sound
//TODO add changing color
//TODO background of timer should gleam
//TODO add countdown from 0 to up
public class TimerCountdown {
    private List<TimeDisplayController> controllers = new ArrayList<>();
    private boolean pause = true;
    private boolean stop = true;
    private int lastMaxTime = 0;
    private int time = 0;
    private Thread countdown;

    private void countDown() {
        if(!pause) {
            time--;
            notifyControllers();
        }
    }

    private void notifyControllers() {
        for(TimeDisplayController display:controllers){
            display.setTime(time);
        }
    }

    public void stop() {
        setPause(true);
        stop = true;
        setTime(0);
    }

    public void startTime(int time) {
        setTime(time);
        setPause(false);
        stop = false;
    }

    public boolean isStop() {
        return stop;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
        if(!pause) {
            reloadThread();
            countdown.start();
        }
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.lastMaxTime = time;
        this.time = time;
        notifyControllers();
    }

    public void addController(TimeDisplayController controller) {
        if(!isOnList(controller)) {
            controllers.add(controller);
        }
    }

    public void removeController(TimeDisplayController controller) {
        TimeDisplayController display = getControllerById(controller.getId());
        if(display!=null) {
            controllers.remove(display);
        }
    }

    private boolean isOnList(TimeDisplayController controller) {
        return getControllerById(controller.getId()) != null;
    }

    @Nullable
    private TimeDisplayController getControllerById(String id) {
        for(TimeDisplayController display: controllers){
            if(display.getId().equals(id)) {
                return display;
            }
        }
        return null;
    }

    private void reloadThread() {
        if(countdown!=null) {
            countdown.stop();
        }
        countdown = null;
        countdown = getThread();
    }

    private Thread getThread() {
        return new Thread(() -> {
            while (!pause) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDown();
            }
        });
    }

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
