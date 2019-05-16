package jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.label;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * All rights reserved & copyright ©
 */
public class TimeLabel {

    private final Label label;
    private Paint normalColor = Color.GRAY;
    private boolean gleammingMode = false;

    public TimeLabel(Label label) {
        this.label = label;
    }

    public void enterGleammingMode(){
        gleammingMode = true;
    }

    public void setGleammingColor(Paint paint) {
        label.setTextFill(paint);
    }

    public void leaveGleammingMode(){
        gleammingMode = false;
        setColor(normalColor);
    }

    public void setColor(Paint paint) {
        normalColor = paint;
        if(!gleammingMode) {
            label.setTextFill(paint);
        }
    }

    public void setText(String text) {
        label.setText(text);
    }
}
