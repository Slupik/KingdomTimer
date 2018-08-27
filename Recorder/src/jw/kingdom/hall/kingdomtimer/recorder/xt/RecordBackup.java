package jw.kingdom.hall.kingdomtimer.recorder.xt;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * All rights reserved & copyright ©
 */
class RecordBackup {
    private final BufferDataSaver saver;
    private Thread backupThread;
    private boolean running = false;
    private File lastBackup;

    RecordBackup(BufferDataSaver saver) {
        this.saver = saver;
    }

    void start(int intervalInSeconds) {
        running = true;
        backupThread = new Thread(() -> {
            while(running) {
                try {
                    Thread.sleep(intervalInSeconds*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                backupData();
            }
        });
        backupThread.start();
    }

    void stop(){
        running = false;
        backupThread.stop();
        backupThread = null;
    }

    private void backupData() {
        deleteLastBackupFile();
        saveDataToWav();
    }

    private void saveDataToWav() {
        File backup = new File(getBackupName());
        saver.saveTo(backup);
        lastBackup = backup;
    }

    private String getBackupName() {
        //return UUID.randomUUID() + ".wav";
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
        Date date = new Date();
        return "BACKUP_"+dateFormat.format(date) + ".wav";
    }

    void deleteLastBackupFile() {
        if(null != lastBackup && lastBackup.exists()) {
            lastBackup.delete();
            lastBackup = null;
        }
    }
}
