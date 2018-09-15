package jw.kingdom.hall.kingdomtimer.usecase.backup;

import jw.kingdom.hall.kingdomtimer.usecase.time.controller.TimeController;

/**
 * All rights reserved & copyright ©
 */
public class BackupMaker {

    private final TimeController timeController;

    public BackupMaker(TimeController timeController){
        this.timeController = timeController;
        init();
    }

    private void init() {

    }
}
