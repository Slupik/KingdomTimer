package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl;


import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import static jw.kingdom.hall.kingdomtimer.app.view.utils.ButtonUtils.loadImage;

/**
 * All rights reserved & copyright ©
 */
public class BtnBuzzerController {

    private Button button;
    private MeetingTask task;
    private final ChangeListener<Boolean> buzzerConditionListener = (observable, oldValue, newValue) -> setImageForVolumeUp(newValue);

    public BtnBuzzerController(Button button) {
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
        TimerCountdown.getInstance().addListener(new TimerCountdownListener() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                BtnBuzzerController.this.task = task;
                setImageForCurrentCondition();
            }

            @Override
            public void onStop() {
                super.onStop();
                BtnBuzzerController.this.task = null;
                setImageForCurrentCondition();
            }
        });
    }

    public void loadTask(MeetingTask task) {
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
            loadImage(button, "icons/baseline_volume_up_black_48dp.png");
        } else {
            loadImage(button, "icons/baseline_volume_off_black_48dp.png");
        }
    }
}
