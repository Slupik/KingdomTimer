package jw.kingdom.hall.kingdomtimer.usecase.time.buzzer;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownListener;

/**
 * All rights reserved & copyright ©
 */
public class OLD_BuzzerAutoControllerImpl implements BuzzerAutoController {
    private final BuzzerPlayer player;
    private final CountdownController countdown;
    //Global flag
    private final ObservableField<Boolean> isEnabled = new ObservableField<>(true);

    public OLD_BuzzerAutoControllerImpl(BuzzerPlayer player, CountdownController countdown){
        this.player = player;
        this.countdown = countdown;
        init();
    }

    private void init() {
        countdown.addListener(new CountdownListener() {
            private Task task;

            @Override
            public void onTaskStart(Task task) {
                super.onTaskStart(task);
                this.task = task;
            }

            @Override
            public void onTimeChange(int time) {
                super.onTimeChange(time);
                if(null != task && task.isUseBuzzer() && isEnabled()) {
                    if(time <= 0 && (Math.abs(time)%10)==0) {
                        player.play();
                    }
                }
            }
        });
    }

    @Override
    public ObservableField<Boolean> isEnabledProperty() {
        return isEnabled;
    }

    @Override
    public void setEnabled(Boolean isEnabled) {
        this.isEnabled.setValue(isEnabled);
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled.getValue();
    }

    @Override
    public void addListener(BuzzerListener listener) {

    }

    @Override
    public void removeListener(BuzzerListener listener) {

    }
}
