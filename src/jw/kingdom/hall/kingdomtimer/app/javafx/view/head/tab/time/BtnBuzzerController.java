package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time;


import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.CountdownListenerProxy;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import static jw.kingdom.hall.kingdomtimer.app.javafx.utils.ButtonUtils.loadMediumImage;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnBuzzerController {

    private final Countdown countdown;
    private Button button;
    private MeetingTask task;
    private final ChangeListener<Boolean> buzzerConditionListener = (observable, oldValue, newValue) -> setImageForVolumeUp(newValue);

    public BtnBuzzerController(Countdown countdown, Button button) {
        this.countdown = countdown;
        this.button = button;
        init();
    }

    private void init() {
        setImageForCurrentCondition();
        button.setOnAction(event -> {
            if(null != task) {
                task.setUseBuzzer(!task.isUseBuzzer());
                setImageForCurrentCondition();
            }
        });
        getCountdown().addListener(new CountdownListenerProxy() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                loadTask(task);
                setImageForCurrentCondition();
            }

            @Override
            public void onStop() {
                super.onStop();
                loadTask(null);
                setImageForCurrentCondition();
            }
        });
    }

    private void loadTask(MeetingTask task) {
        if(null != this.task) {
            this.task.useBuzzerProperty().removeListener(buzzerConditionListener);
        }
        this.task = task;
        setImageForCurrentCondition();
        if(null != this.task) {
            this.task.useBuzzerProperty().addListener(buzzerConditionListener);
        }
    }

    private void setImageForCurrentCondition() {
        setImageForVolumeUp((null != task && task.isUseBuzzer()));
    }

    private void setImageForVolumeUp(boolean isVolumeUp) {
        if(isVolumeUp) {
            loadMediumImage(button, "icons/baseline_volume_up_black_48dp.png");
        } else {
            loadMediumImage(button, "icons/baseline_volume_off_black_48dp.png");
        }
    }

    private Countdown getCountdown() {
        return countdown;
    }
}
