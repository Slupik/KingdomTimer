package jw.kingdom.hall.kingdomtimer.javafx.control.time.direct;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.javafx.GuiTimeListener;
import jw.kingdom.hall.kingdomtimer.javafx.mapper.MapperPojoToFxTask;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.CountdownController;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForInstantController extends BtnTimeDirectForObj {
    public BtnTimeDirectForInstantController(CountdownController countdown, Config config, Button button) {
        super(config, button);
        countdown.addListener(new GuiTimeListener() {
            @Override
            public void onStart(TaskPOJO task) {
                loadTask(new MapperPojoToFxTask().map(task));
            }

            @Override
            public void onStop() {
                super.onStop();
                loadTask(null);
            }
        });
    }
}
