package jw.kingdom.hall.kingdomtimer.domain.backup;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BackupManager {
    private static boolean running = false;

    private static TimeBackupMaker time;

    public static void start(){
        if(!running) {
            running = true;
        } else {
            return;
        }
        init();
    }

    private static void init() {
        time = new TimeBackupMaker();
    }
}
