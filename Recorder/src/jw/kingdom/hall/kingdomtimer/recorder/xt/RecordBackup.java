package jw.kingdom.hall.kingdomtimer.recorder.xt;

import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;

import java.io.File;

/**
 * All rights reserved & copyright ©
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
        backupThread.stop();
        backupThread = null;
        deleteLastBackupFile();
        lastBackup=null;
    }

    private void backupData() {
        deleteLastBackupFile();
        saveDataToWav();
    }

    private void saveDataToWav() {
        File backup = getDestFile();
        saver.saveTo(backup);
        lastBackup = backup;
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
