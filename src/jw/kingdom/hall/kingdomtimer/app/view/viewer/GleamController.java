package jw.kingdom.hall.kingdomtimer.app.view.viewer;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * All rights reserved & copyright ©
 */
public class GleamController {
    private Pane pane;
    private boolean isPlaying = false;
    private Background defaultBackground;

    public GleamController(Pane pane) {
        this.pane = pane;
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
                sleep(250);
                pane.setBackground(defaultBackground);
                sleep(250);
            }
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
