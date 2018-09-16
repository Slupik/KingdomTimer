package jw.kingdom.hall.kingdomtimer.domain.backup;

import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BackupManager {
    private static boolean running = false;

    private static TimeBackupMaker timeBackup;
    private static TimeBackupRestorer timeRestore;

    public static void restore(){
        timeRestore.restore();
    }

    public static void delete() {
        timeRestore.delete();
    }

    public static boolean isBackupAvailable(){
        if(timeRestore.isAvailable()) {
            return true;
        }
        return false;
    }

    public static void start(RecordControl recordControl, Schedule schedule){
        if(!running) {
            running = true;
        } else {
            return;
        }
        init(recordControl, schedule);
    }

    private static void init(RecordControl recordControl, Schedule schedule) {
        timeRestore = new TimeBackupRestorer(recordControl, schedule);
        timeBackup = new TimeBackupMaker(recordControl, schedule);
    }
}
