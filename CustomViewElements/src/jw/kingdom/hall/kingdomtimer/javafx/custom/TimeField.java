package jw.kingdom.hall.kingdomtimer.javafx.custom;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * All rights reserved & copyright ©
 */
public class TimeField extends TextField {


    public TimeField(){
        setText("00:00:00");
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
                String formattedText = TimeFieldUtils.getFormattedText(newValue);
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
}
