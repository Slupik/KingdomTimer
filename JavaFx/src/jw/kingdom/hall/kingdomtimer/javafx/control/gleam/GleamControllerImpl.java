package jw.kingdom.hall.kingdomtimer.javafx.control.gleam;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.TimeDisplay;
import jw.kingdom.hall.kingdomtimer.entity.time.gleam.GleamController;
import jw.kingdom.hall.kingdomtimer.entity.time.gleam.GleamSwitcher;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class GleamControllerImpl implements GleamController {
    private Pane pane;
    private TimeDisplay time;
    private final GleamSwitcher switcher;
    private boolean isPlaying = false;
    private Background defaultBackground;

    public GleamControllerImpl(Pane pane, TimeDisplay time, GleamSwitcher switcher) {
        this.pane = pane;
        this.time = time;
        this.switcher = switcher;
    }

    public void play(){
        if(isPlaying || !switcher.isEnabled()) {
            return;
        }
        isPlaying = true;
        defaultBackground = pane.getBackground();
        new Thread(() -> {
            for(int i=0;i<5;i++) {
                pane.setBackground(getGleamingBackground());
                time.setTextColor(defaultBackground.getFills().get(0).getFill());
                sleep(250);
                pane.setBackground(defaultBackground);
                time.setTextColor(getGleamingBackground().getFills().get(0).getFill());
                sleep(250);
            }
            time.resetColorToLast();
            isPlaying = false;
        }).start();
    }

    private Background getGleamingBackground() {
        return new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY));
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
