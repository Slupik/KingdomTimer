package jw.kingdom.hall.kingdomtimer.view.common.controller;

import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

/**
 * All rights reserved & copyright ©
 */
public class TimeDisplayController {

    private Label text;

    public TimeDisplayController(Label text) {
        this.text = text;
    }

    public void setColor(Paint paint) {
        text.setTextFill(paint);
    }

    public void setTime(int seconds){
        String data = secondsToText(seconds);
        if(Platform.isFxApplicationThread()) {
            setText(data);
        } else {
            Platform.runLater(()-> setText(data));
        }
    }

    private void setText(String text) {
        this.text.setText(text);
    }

    private static String secondsToText(int time) {
        int hours = time/3600;
        int minutes = time/60;
        int seconds = time%60;
        return getFormattedNumber(hours)+":"+getFormattedNumber(minutes)+":"+getFormattedNumber(seconds);
    }

    private static String getFormattedNumber(int number) {
        if(number<10) {
            return "0"+Integer.toString(number);
        }
        return Integer.toString(number);
    }
}
