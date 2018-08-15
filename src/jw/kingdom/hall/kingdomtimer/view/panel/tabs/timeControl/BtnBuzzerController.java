package jw.kingdom.hall.kingdomtimer.view.panel.tabs.timeControl;


import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import static jw.kingdom.hall.kingdomtimer.view.utils.ButtonUtils.loadImage;

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
    }

    public void loadTask(MeetingTask task) {
        task.useBuzzerProperty().removeListener(buzzerConditionListener);
        this.task = task;
        this.task.useBuzzerProperty().addListener(buzzerConditionListener);
        setImageForCurrentCondition();
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
