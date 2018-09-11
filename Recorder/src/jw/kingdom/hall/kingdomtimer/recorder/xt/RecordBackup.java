package jw.kingdom.hall.kingdomtimer.recorder.xt;

import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;

import java.io.File;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class RecordBackup {
    private final BufferDataSaver saver;
    private final AudioSettingsBean bean;
    private Thread backupThread;
    private boolean running = false;
    private File lastBackup;

    RecordBackup(BufferDataSaver saver, AudioSettingsBean bean) {
        this.saver = saver;
        this.bean = bean;
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

    @SuppressWarnings("deprecation")
    void stop(){
        running = false;
        if(backupThread!=null) backupThread.stop();
        backupThread = null;
        deleteLastBackupFile();
        lastBackup=null;
    }

    private void backupData() {
        File newestBackup = saveDataToWav();
        deleteLastBackupFile();
        lastBackup = newestBackup;
    }

    private File saveDataToWav() {
        File backup = getDestFile();
        saver.saveBackupTo(backup);
        return backup;
    }

    private File getDestFile() {
        return bean.getPaths().getBackupFile(".wav");
    }

    private void deleteLastBackupFile() {
        if(null != lastBackup && lastBackup.exists()) {
            lastBackup.delete();
            lastBackup = null;
        }
    }
}
