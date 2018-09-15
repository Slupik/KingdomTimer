package jw.kingdom.hall.kingdomtimer.usecase.time.gleam;

import jw.kingdom.hall.kingdomtimer.usecase.time.controller.TimeController;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class GleamControllerImpl extends GleamLogic {

    private final List<Gleammer> gleammers = new ArrayList<>();

    public GleamControllerImpl(TimeController timeController) {
        super(timeController);
    }

    @Override
    protected void startGleamming() {
        for(Gleammer gleammer:gleammers) {
            gleammer.gleam();
        }
    }

    @Override
    public void addGleammer(Gleammer gleammer) {
        gleammers.add(gleammer);
    }

    @Override
    public void removeGleammer(Gleammer gleammer) {
        gleammers.remove(gleammer);
    }
}
