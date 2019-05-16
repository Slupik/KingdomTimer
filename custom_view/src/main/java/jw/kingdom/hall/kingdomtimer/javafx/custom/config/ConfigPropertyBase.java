package jw.kingdom.hall.kingdomtimer.javafx.custom.config;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * All rights reserved & copyright ©
 */
public abstract class ConfigPropertyBase<ValueType> extends VBox {

    protected Button reset;
    protected Label title;
    protected Label desc;
    protected ValueType defaultValue;

    public ConfigPropertyBase(){
        reset = new Button("Default");
        reset.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resetValue();
            }
        });

        title = new Label("Property: ");
        title.setFont(Font.font(title.getFont().getName(), FontWeight.BOLD, 14));
        title.setAlignment(Pos.TOP_CENTER);

        desc = new Label();

        fillWithElements();
    }

    private void fillWithElements() {
        title.setPadding(new Insets(0, 0, 4, 0));

        desc.setPadding(new Insets(8, 0, 0, 0));
        desc.setFont(new Font(desc.getFont().getName(), 16));

        HBox.setMargin(getCustomNode(), new Insets(0,0,0,8));

        HBox container = new HBox();
        container.getChildren().addAll(reset, getCustomNode());
        getChildren().addAll(title, container, desc);

        showDesc(false);
    }

    protected abstract Region getCustomNode();

    protected abstract void resetValue();

    public abstract ValueType getResult();

    public void setResetValue(ValueType defaultValue) {
        this.defaultValue = defaultValue;
    }

    public abstract void setValue(ValueType newValue);

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDesc(String desc) {
        this.desc.setText(desc);
        if(desc==null || "".equals(desc)) {
            showDesc(false);
        } else {
            showDesc(true);
        }
    }

    public Button getResetButton() {
        return reset;
    }

    private void showDesc(boolean show) {
        desc.setVisible(show);
        desc.setManaged(show);
    }
}
