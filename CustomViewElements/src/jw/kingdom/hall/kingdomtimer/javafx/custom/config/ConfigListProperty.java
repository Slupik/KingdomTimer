package jw.kingdom.hall.kingdomtimer.javafx.custom.config;

import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Region;

/**
 * All rights reserved & copyright ©
 */
public class ConfigListProperty<ValueType> extends ConfigPropertyBase<ValueType> {

    private ChoiceBox<ValueType> box;

    @Override
    protected Region getCustomNode() {
        if(box==null) {
            box = new ChoiceBox<>();
            box.setPrefWidth(150);
            resetValue();
        }
        return box;
    }

    @Override
    protected void resetValue() {
        box.setValue(defaultValue);
    }

    @Override
    public ValueType getResult() {
        return box.getValue();
    }

    @Override
    public void setValue(ValueType newValue) {
        box.setValue(newValue);
    }
}
