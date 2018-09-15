package jw.kingdom.hall.kingdomtimer.usecase.time.buzzer;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer;
import jw.kingdom.hall.kingdomtimer.usecase.time.controller.CountdownController;
import jw.kingdom.hall.kingdomtimer.usecase.time.controller.TimeDisplayProxy;

/**
 * All rights reserved & copyright ©
 */
public abstract class BuzzerLogic implements BuzzerAutoController {
    private final BuzzerPlayer player;
    private final CountdownController countdown;
    //Global flag
    protected final ObservableField<Boolean> isEnabled = new ObservableField<>(true);

    public BuzzerLogic(BuzzerPlayer player, CountdownController countdown){
        this.player = player;
        this.countdown = countdown;
        addCountdownListener();
    }

    private void addCountdownListener() {
        countdown.addDisplay(new TimeDisplayProxy() {

            private Task task;

            @Override
            public void setTask(Task task) {
                this.task = task;
            }

            @Override
            public void display(int startTime, int timeToDisplay, int absoluteTimeLeft) {
                if(null != task && task.isUseBuzzer() && isEnabled()) {
                    if(absoluteTimeLeft <= 0 && (Math.abs(absoluteTimeLeft)%10)==0) {
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
        if(isEnabled) {
            eventOnEnable();
        } else {
            eventOnDisable();
        }
    }

    protected abstract void eventOnEnable();

    protected abstract void eventOnDisable();

    @Override
    public boolean isEnabled() {
        return isEnabled.getValue();
    }
}
