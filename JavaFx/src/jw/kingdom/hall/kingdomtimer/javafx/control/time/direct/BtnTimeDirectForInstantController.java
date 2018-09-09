package jw.kingdom.hall.kingdomtimer.javafx.control.time.direct;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownListener;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleListener;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForInstantController extends BtnTimeDirectForObj {
    public BtnTimeDirectForInstantController(CountdownController countdown, Config config, Button button) {
        super(config, button);
        countdown.addListener(new CountdownListener() {
            @Override
            public void onTaskStart(Task task) {
                super.onTaskStart(task);
                if(task instanceof TaskFxBean) {
                    loadTask(((TaskFxBean) task));
                } else if(task instanceof ObservableTask){
                    loadTask(new TaskFxBean(((ObservableTask) task)));
                } else {
                    loadTask(new TaskFxBean(task));
                }
            }

            @Override
            protected void onStop() {
                super.onStop();
                loadTask(null);
            }
        });
    }
}
