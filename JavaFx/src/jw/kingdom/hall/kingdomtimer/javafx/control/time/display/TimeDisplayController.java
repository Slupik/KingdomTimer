package jw.kingdom.hall.kingdomtimer.javafx.control.time.display;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.javafx.utils.PlatformUtils;

import java.util.ArrayList;
import java.util.List;

import static jw.kingdom.hall.kingdomtimer.javafx.control.time.display.TextFormatter.secondsToText;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeDisplayController implements TimeDisplay {
    private final List<Listener> listeners = new ArrayList<>();
    private Label text;
    private boolean isLightBackground = true;
    private int lastColorCode = -1;
    private int lastTextSize = -1;

    public TimeDisplayController(Label text) {
        this.text = text;
        setTime(0);
    }

    @Override
    public void setLightBackground(boolean lightBackground) {
        isLightBackground = lightBackground;
        setColor(TimerColor.getColor(lastColorCode, isLightBackground));
    }

    @Override
    public void onTaskChange(Task newTask) {}

    @Override
    public void display(int startTime, int timeToDisplay, int absoluteTimeLeft) {
        setTime(timeToDisplay);
        setColorCode(TimerColor.getColorCode(startTime, absoluteTimeLeft));
    }

    @Override
    public void reset() {
        setTime(0);
        setColorCode(TimerColor.getDefaultColorCode());
    }

    @Override
    public void resetColorToLast(){
        setColor(TimerColor.getColor(lastColorCode, isLightBackground));
    }

    private void setColorCode(int paintCode) {
        if(lastColorCode!=paintCode) {
            setColor(TimerColor.getColor(paintCode, isLightBackground));
            lastColorCode = paintCode;
        }
    }

    private void setColor(Paint paint) {
        text.setTextFill(paint);
    }

    private void setTime(int seconds){
        final String text = secondsToText(seconds);
        PlatformUtils.runOnUiThread(()-> {
            setText(text);
            notifyTextSizeChanged(text.length());
        });
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
