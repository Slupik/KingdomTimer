package jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.timedirect;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.app.view.panel.tabs.timeControl.BtnBuzzerController;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
 */
public class BtnTimeDirectForInstantController extends BtnTimeDirectForObj {
    public BtnTimeDirectForInstantController(Button button) {
        super(button);
        TimerCountdown.getInstance().addListener(new TimerCountdownListener() {
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
