package jw.kingdom.hall.kingdomtimer.domain.file;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;

import java.io.File;

/**
 * All rights reserved & copyright ©
 */
public interface FileManager {

    FileRecordCreator getFileRecordCreator(Config config, Schedule schedule, Countdown countdown);

    File getScheduleFile();
    void deleteRootPath();
}
