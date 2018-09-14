package jw.kingdom.hall.kingdomtimer.usecase.backup;

import jw.kingdom.hall.kingdomtimer.usecase.time.controller.MeetingTimeController;

/**
 * All rights reserved & copyright ©
 */
public class BackupMaker {

    private final MeetingTimeController timeController;

    public BackupMaker(MeetingTimeController timeController){
        this.timeController = timeController;
        init();
    }

    private void init() {

    }
}
