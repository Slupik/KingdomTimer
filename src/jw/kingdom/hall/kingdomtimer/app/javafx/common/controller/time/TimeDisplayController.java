package jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.domain.utils.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeDisplayController implements TimeDisplay {
    private final String ID = Randomizer.randomStandardString(10);
    private final List<Listener> listeners = new ArrayList<>();

    private Label text;

    private boolean isLightBackground = true;
    private int lastColorCode = -1;

    private int lastTextSize = -1;

    public TimeDisplayController(Label text) {
        this.text = text;
    }

    @Override
    public void display(int startTime, int timeToDisplay, int absoluteTimeLeft) {
        setTime(timeToDisplay);
        setColorCode(TimerColor.getColorCode(startTime, absoluteTimeLeft));
    }

    @Override
    public void display(int time) {
        setTime(time);
    }

    @Override
    public void reset() {
        setTime(0);
        setColorCode(TimerColor.getDefaultColorCode());
    }

    @Override
    public void setTask(MeetingTask task) {}
    @Override
    public void onTimeOut() {}

    public void setLightBackground(boolean lightBackground) {
        isLightBackground = lightBackground;
        setColor(TimerColor.getColor(lastColorCode, isLightBackground));
    }

    public void setColor(Paint paint) {
        text.setTextFill(paint);
    }

    public void resetColorToLast(){
        setColor(TimerColor.getColor(lastColorCode, isLightBackground));
    }

    public void setTime(int seconds){
        String data = secondsToText(seconds);
        if(Platform.isFxApplicationThread()) {
            setText(data);
            notifyTextSizeChanged(data.length());
        } else {
            Platform.runLater(()-> {
                setText(data);
                notifyTextSizeChanged(data.length());
            });
        }
    }

    private void setColorCode(int paintCode) {
        if(lastColorCode!=paintCode) {
            setColor(TimerColor.getColor(paintCode, isLightBackground));
            lastColorCode = paintCode;
        }
    }

    private void setText(String text) {
        this.text.setText(text);
    }

    private static String getFormattedNumber(int number) {
        if(number<10) {
            return "0"+Integer.toString(number);
        }
        return Integer.toString(number);
    }

    private static String secondsToText(int time) {
        boolean isSmallerThanZero = time<0;
        if(isSmallerThanZero) {
            time = Math.abs(time);
        }
        int hours = time/3600;
        int minutes = (time%3600)/60;
        int seconds = time%60;
        String basic = getFormattedNumber(hours)+":"+getFormattedNumber(minutes)+":"+getFormattedNumber(seconds);
        if(basic.startsWith("00:")) {
            basic = basic.substring(3);
        }
        if(isSmallerThanZero) {
            return "-"+basic;
        } else {
            return basic;
        }
    }

    private void notifyTextSizeChanged(int length) {
        if(lastTextSize!=length) {
            lastTextSize = length;
            for(Listener listener:listeners) {
                listener.onTextSizeChanged();
            }
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public interface Listener {
        void onTextSizeChanged();
    }
}
