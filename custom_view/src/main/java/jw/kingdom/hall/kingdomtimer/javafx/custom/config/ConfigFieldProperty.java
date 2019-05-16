package jw.kingdom.hall.kingdomtimer.javafx.custom.config;

import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.javafx.custom.AdvancedTextField;

/**
 * All rights reserved & copyright ©
 */
public class ConfigFieldProperty extends ConfigPropertyBase<String> {

    private AdvancedTextField field;

    public void setRestrict(String restrict) {
        field.setRestrict(restrict);
    }

    @Override
    protected Region getCustomNode() {
        if(field==null) {
            field = new AdvancedTextField();
            field.setPrefHeight(25);
            resetValue();
        }
        return field;
    }

    @Override
    protected void resetValue() {
        field.setText(defaultValue);
    }

    @Override
    public String getResult() {
        return field.getText();
    }

    @Override
    public void setValue(String newValue) {
        field.setText(newValue);
    }

}
