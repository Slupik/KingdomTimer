package jw.kingdom.hall.kingdomtimer.domain.backup;

import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;

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

    public static void start(RecordControl recordControl){
        if(!running) {
            running = true;
        } else {
            return;
        }
        init(recordControl);
    }

    private static void init(RecordControl recordControl) {
        timeRestore = new TimeBackupRestorer(recordControl);
        timeBackup = new TimeBackupMaker(recordControl);
    }
}
