package jw.kingdom.hall.kingdomtimer.javafx.custom;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeField extends TextField {
    //Alternative - 3 fields instead only one
//    setAlignment(Pos.CENTER);
//    setBackground(Background.EMPTY);
//    setBorder(Border.EMPTY);
//    setPadding(Insets.EMPTY);
//    setPrefColumnCount(2);

    public TimeField(){
        clear();
        setPrefColumnCount(8);
        addListener();
    }

    private void addListener() {
        textProperty().addListener(new ChangeListener<String>() {
            private boolean ignore;

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (ignore)
                    return;
                ignore=true;
                String formattedText = TimeFieldUtils.getFormattedText(oldValue, newValue);
                setText(formattedText);
                Platform.runLater(()-> TimeField.this.positionCaret(
                        calculateCaretPosition(getCaretPosition(), oldValue, formattedText))
                );
                ignore=false;
            }
        });
    }

    private int calculateCaretPosition(int oldPos, String oldText, String newText) {
        // "0|0" -> "01|0" "01|"
        for(int i=oldPos;i<newText.length();i++) {
            if(oldText.charAt(i)!=newText.charAt(i)) {
                return ++i;
            }
        }
        return oldPos;
    }

    public int getAllSeconds() {
        return TimeFieldUtils.getAllSeconds(getText());
    }

    public void setSeconds(int time) {
        setText(TimeFieldUtils.secondsToText(time));
    }

    @Override
    public void clear() {
        setText("00:00:00");
    }
}
