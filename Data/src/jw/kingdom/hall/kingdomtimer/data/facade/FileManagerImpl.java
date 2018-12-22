package jw.kingdom.hall.kingdomtimer.data.facade;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.record.DefaultFileRecordCreator;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.file.FileManager;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;

import java.io.File;

/**
 * All rights reserved & copyright ©
 */
public class FileManagerImpl implements FileManager {

    @Override
    public FileRecordCreator getFileRecordCreator(Config config, Schedule schedule, Countdown countdown) {
        return new DefaultFileRecordCreator(config, schedule, countdown);
    }

    @Override
    public File getScheduleFile() {
        return BackupFileManager.getScheduleFile();
    }

    @Override
    public void deleteRootPath() {
        BackupFileManager.deleteRootPath();
    }

}
