package jw.kingdom.hall.kingdomtimer.domain.backup;

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

    public static void start(){
        if(!running) {
            running = true;
        } else {
            return;
        }
        init();
    }

    private static void init() {
        timeRestore = new TimeBackupRestorer();
        timeBackup = new TimeBackupMaker();
    }
}
