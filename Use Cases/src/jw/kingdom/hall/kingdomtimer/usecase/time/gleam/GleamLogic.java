package jw.kingdom.hall.kingdomtimer.usecase.time.gleam;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.usecase.time.controller.TimeController;
import jw.kingdom.hall.kingdomtimer.usecase.time.display.TimeDisplayProxy;

/**
 * All rights reserved & copyright ©
 */
public abstract class GleamLogic implements GleamController {

    private final ObservableField<Boolean> enabled = new ObservableField<>();

    public GleamLogic(TimeController timeController){
        timeController.addDisplay(new TimeDisplayProxy<Task>() {
            @Override
            public void onTimeOut() {
                startGleamming();
            }
        });
    }

    protected abstract void startGleamming();

    @Override
    public void enable() {
        enabled.setValue(true);
    }

    @Override
    public void disable() {
        enabled.setValue(false);
    }

    @Override
    public boolean isEnabled() {
        return enabled.getValue();
    }
}
