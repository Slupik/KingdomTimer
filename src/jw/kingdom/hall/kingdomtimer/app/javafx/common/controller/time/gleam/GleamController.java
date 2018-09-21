package jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.gleam;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.app.javafx.common.controller.time.label.TimeLabel;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class GleamController {
    private GleammingElement view;
    private boolean isPlaying = false;
    private Background defaultBackground;

    public GleamController(Region pane, TimeLabel label) {
        this(new GleammingElementImpl(pane, label));
    }

    public GleamController(GleammingElement element) {
        this.view = element;
    }

    public void play(){
        if(isPlaying) {
            return;
        }
        isPlaying = true;

        new Thread(() -> {

            view.startGleamming();
            defaultBackground = view.getDefaultBackground();

            for(int i=0;i<5;i++) {

                view.applyColors(getGleamingBackground(),
                        defaultBackground.getFills().get(0).getFill());
                sleep(250);

                view.applyColors(defaultBackground,
                        getGleamingBackground().getFills().get(0).getFill());
                sleep(250);
            }
            view.endGleamming();
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
