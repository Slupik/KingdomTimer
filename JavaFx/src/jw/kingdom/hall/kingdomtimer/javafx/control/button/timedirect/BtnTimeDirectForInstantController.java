package jw.kingdom.hall.kingdomtimer.javafx.control.button.timedirect;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.model.task.ObservableTask;
import jw.kingdom.hall.kingdomtimer.model.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.model.time.schedule.ScheduleListener;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForInstantController extends BtnTimeDirectForObj {
    public BtnTimeDirectForInstantController(ScheduleController schedule, Config config, Button button) {
        super(config, button);
        schedule.addListener(new ScheduleListener() {
            @Override
            public void onListChange(List<ObservableTask> list) {
                super.onListChange(list);
                //TODO add event onStart onStop and implement this
//                loadTask(task);
//                loadTask(null);
            }
        });
    }
}
