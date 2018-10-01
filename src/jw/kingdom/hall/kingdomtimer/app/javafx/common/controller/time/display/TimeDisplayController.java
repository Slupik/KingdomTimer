package jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.display;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.label.TimeLabel;
import jw.kingdom.hall.kingdomtimer.app.javafx.utils.PlatformUtils;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplay;

import java.util.ArrayList;
import java.util.List;

import static jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.display.TextFormatter.getFormattedTime;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeDisplayController implements TimeDisplay {
    private final List<Listener> listeners = new ArrayList<>();

    private TimeLabel text;

    private boolean isLightBackground = true;
    private int lastColorCode = -1;

    private int lastTextSize = -1;

    public TimeDisplayController(Label text) {
        this(new TimeLabel(text));
    }

    public TimeDisplayController(TimeLabel text) {
        this.text = text;
        reset();
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
    public void setTask(TaskBean task) {}
    @Override
    public void onTimeOut() {}

    public void setLightBackground(boolean lightBackground) {
        isLightBackground = lightBackground;
        setTextColor(TimerColor.getColor(lastColorCode, isLightBackground));
    }

    private void setTime(int seconds){
        String data = getFormattedTime(seconds);
        PlatformUtils.runOnUiThread(()->{
            setText(data);
            notifyTextSizeChanged(data.length());
        });
    }

    private void setColorCode(int paintCode) {
        if(lastColorCode!=paintCode) {
            setTextColor(TimerColor.getColor(paintCode, isLightBackground));
            lastColorCode = paintCode;
        }
    }

    private void setTextColor(Paint paint) {
        text.setColor(paint);
    }

    private void setText(String text) {
        this.text.setText(text);
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
