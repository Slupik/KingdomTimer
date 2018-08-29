package jw.kingdom.hall.kingdomtimer.recorder.xt;

import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String withoutExtension = getFilenameWithoutExtension();
        File file = new File(getRootPath() + withoutExtension + ".wav");
        int index = 1;
        while (file.exists()) {
            file = new File(getRootPath() + withoutExtension + "[" + Integer.toString(index) + "]" + ".wav");
            index++;
        }
        return file;
    }

    private String getRootPath() {
        if(bean.getDestinationFolder()==null || bean.getDestinationFolder().length()==0) {
            return "";
        } else {
            return bean.getDestinationFolder() + File.separator;
        }
    }

    private String getFilenameWithoutExtension() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        Date date = new Date();
        return "BACKUP_"+dateFormat.format(date);
    }

    void deleteLastBackupFile() {
        if(null != lastBackup && lastBackup.exists()) {
            lastBackup.delete();
            lastBackup = null;
        }
    }
}
