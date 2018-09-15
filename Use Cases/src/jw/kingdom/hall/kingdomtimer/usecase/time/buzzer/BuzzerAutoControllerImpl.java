package jw.kingdom.hall.kingdomtimer.usecase.time.buzzer;

import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer;
import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.CountdownController;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class BuzzerAutoControllerImpl extends BuzzerLogic {

    private final List<BuzzerListener> listeners = new ArrayList<>();

    public BuzzerAutoControllerImpl(BuzzerPlayer player, CountdownController countdown) {
        super(player, countdown);
    }

    @Override
    protected void eventOnEnable() {
        for(BuzzerListener listener:listeners) {
            listener.onEnable();
        }
    }

    @Override
    protected void eventOnDisable() {
        for(BuzzerListener listener:listeners) {
            listener.onDisable();
        }
    }

    @Override
    public void addListener(BuzzerListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(BuzzerListener listener) {
        listeners.remove(listener);
    }
}
