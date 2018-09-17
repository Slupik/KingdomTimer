package jw.kingdom.hall.kingdomtimer.app.javafx.view.head.tab.time.timedirect;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.CountdownListenerProxy;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForInstantController extends BtnTimeDirectForObj {
    public BtnTimeDirectForInstantController(Countdown countdown, Config config, Button button) {
        super(config, button);
        countdown.addListener(new CountdownListenerProxy() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                loadTask(task);
            }

            @Override
            public void onStop() {
                super.onStop();
                loadTask(null);
            }
        });
    }
}
