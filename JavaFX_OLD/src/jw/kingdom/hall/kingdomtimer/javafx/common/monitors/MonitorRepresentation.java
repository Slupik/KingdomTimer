package jw.kingdom.hall.kingdomtimer.javafx.common.monitors;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;

/**
 * All rights reserved & copyright ©
 */
class MonitorRepresentation extends StackPane {

    private final double width;
    private final double height;
    private final Monitor monitor;
    private Label mName;
    private Rectangle mBackground;
    private Rectangle mBackgroundBorder;

    MonitorRepresentation(Monitor monitor) {
        this.width = monitor.getWidth()/10;
        this.height = monitor.getHeight()/10;
        this.monitor = monitor;
        setPrefSize(width, height);

        createBackground();
        setName(getName(monitor));
        select(false);
    }

    private void createBackground() {
        mBackgroundBorder = new Rectangle();
        mBackgroundBorder.setWidth(width);
        mBackgroundBorder.setHeight(height);
        getChildren().add(mBackgroundBorder);

        mBackground = new Rectangle();
        mBackground.setWidth(width-10);
        mBackground.setHeight(height-10);
        getChildren().add(mBackground);
    }

    private void setName(String name) {
        mName = new Label(name);
        mName.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(mName);
    }

    private String getName(Monitor monitor) {
        String name = ((int) monitor.getWidth())+" x "+ ((int) monitor.getHeight())+'\n'+"Wyświetlacz "+monitor.getPlace();
        if(monitor.isPrimary()) {
            name += " (Główny)";
        }
        return name;
    }

    Monitor getMonitor() {
        return monitor;
    }

    void select(boolean select) {
        if(select) {
            mBackground.setFill(Color.LIGHTGRAY);
            mBackgroundBorder.setFill(Color.LIGHTBLUE);

            mName.setTextFill(Color.BLACK);
        } else {
            mBackground.setFill(Color.DARKGRAY);
            mBackgroundBorder.setFill(Color.web("#7c7c7c"));

            mName.setTextFill(Color.WHITE);
        }
    }
}
