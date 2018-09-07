package jw.kingdom.hall.kingdomtimer.javafx.control.time.buzzer;


import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownListener;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;

import static jw.kingdom.hall.kingdomtimer.javafx.utils.ButtonUtils.loadMediumImage;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnBuzzerController {

    private final Button button;
    private final CountdownController countdown;
    private TaskFxBean task;
    private final ChangeListener<Boolean> buzzerConditionListener = (observable, oldValue, newValue) -> setImageForVolumeUp(newValue);

    public BtnBuzzerController(Button button, CountdownController countdown) {
        this.button = button;
        this.countdown = countdown;
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
        countdown.addListener(new CountdownListener() {
            @Override
            public void onTaskStart(Task task) {
                super.onTaskStart(task);
                loadTask(new TaskFxBean(task));
                setImageForCurrentCondition();
            }

            @Override
            protected void onStop() {
                super.onStop();
                loadTask(null);
                setImageForCurrentCondition();
            }
        });
    }

    private void loadTask(TaskFxBean task) {
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
}
