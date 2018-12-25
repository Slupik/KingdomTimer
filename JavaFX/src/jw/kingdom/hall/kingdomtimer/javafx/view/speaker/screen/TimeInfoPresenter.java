package jw.kingdom.hall.kingdomtimer.javafx.view.speaker.screen;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.display.TimeDisplayController;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.gleam.GleamController;
import jw.kingdom.hall.kingdomtimer.javafx.common.controller.time.label.TimeLabel;
import jw.kingdom.hall.kingdomtimer.domain.countdown.gleam.GlobalGleamController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeDisplayProxy;

/**
 * All rights reserved & copyright ©
 */
class TimeInfoPresenter {

    private final Input input;

    TimeInfoPresenter(Input input) {
        this.input = input;
        init();
    }

    private void init() {
        TimeLabel timeLabel = new TimeLabel(getTimeLbl());
        setupTimeDisplay(timeLabel);
        setupGleam(timeLabel);
    }

    private void setupTimeDisplay(TimeLabel timeLabel) {
        TimeDisplayController timeDisplay = new TimeDisplayController(timeLabel, true);
        timeDisplay.setLightBackground(false);
        getTimer().addDisplay(timeDisplay);
    }

    private void setupGleam(TimeLabel timeLabel) {
        GleamController gleammer = new GleamController(getBackgroundForTime(), timeLabel);
        getTimer().addDisplay(new TimeDisplayProxy() {
            @Override
            public void onTimeOut() {
                super.onTimeOut();
                if(GlobalGleamController.getInstance().isEnable()) {
                    gleammer.play();
                }
            }
        });
    }

    private TimeController getTimer() {
        return input.getTimer();
    }

    private Label getTimeLbl() {
        return input.getTimeView();
    }

    private Region getBackgroundForTime(){
        return input.getBackgroundForTime();
    }

    interface Input {
        Label getTimeView();
        Region getBackgroundForTime();
        TimeController getTimer();
    }
}
