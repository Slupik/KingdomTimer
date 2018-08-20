package jw.kingdom.hall.kingdomtimer.app.view.viewer;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import jw.kingdom.hall.kingdomtimer.app.view.common.controller.TimeDisplayController;

/**
 * All rights reserved & copyright ©
 */
public class GleamController {
    private Pane pane;
    private TimeDisplayController time;
    private boolean isPlaying = false;
    private Background defaultBackground;

    public GleamController(Pane pane, TimeDisplayController time) {
        this.pane = pane;
        this.time = time;
    }

    public void play(){
        if(isPlaying) {
            return;
        }
        isPlaying = true;
        defaultBackground = pane.getBackground();
        new Thread(() -> {
            for(int i=0;i<5;i++) {
                pane.setBackground(getGleamingBackground());
                time.setColor(defaultBackground.getFills().get(0).getFill());
                sleep(250);
                pane.setBackground(defaultBackground);
                time.setColor(getGleamingBackground().getFills().get(0).getFill());
                sleep(250);
            }
            time.resetColorToLast();
            isPlaying = false;
        }).start();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Background getGleamingBackground() {
        return new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY));
    }
}
