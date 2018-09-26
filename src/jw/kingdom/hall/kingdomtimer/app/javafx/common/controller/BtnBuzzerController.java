package jw.kingdom.hall.kingdomtimer.app.javafx.common.controller;


import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;

import static jw.kingdom.hall.kingdomtimer.app.javafx.utils.ButtonUtils.loadMediumImage;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnBuzzerController {

    private final TimeController timer;
    private Button button;
    private MeetingTask task;
    private final ChangeListener<Boolean> buzzerConditionListener = (observable, oldValue, newValue) -> setImageForVolumeUp(newValue);

    public BtnBuzzerController(TimeController timer, Button button) {
        this.timer = timer;
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
        getTimer().addListener(new TimeListenerProxy() {
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

    private TimeController getTimer() {
        return timer;
    }
}
