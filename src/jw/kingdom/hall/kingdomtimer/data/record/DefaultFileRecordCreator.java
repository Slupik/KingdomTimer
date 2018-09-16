package jw.kingdom.hall.kingdomtimer.data.record;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.model.ConfigReadable;
import jw.kingdom.hall.kingdomtimer.data.UniqueFileUtils;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;

import java.io.File;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class DefaultFileRecordCreator implements FileRecordCreator {

    private static final MeetingTask EMPTY = getEmptyTask();
    private final Config config;
    private MeetingTask lastTask = EMPTY;

    public DefaultFileRecordCreator(Config config, Schedule schedule, Countdown countdown){
        this.config = config;
        countdown.addListener(new TimerCountdownListener() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                lastTask = task;
            }

            @Override
            public void onStop() {
                super.onStop();
                if(schedule.getList().size()==0){
                    new Thread(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lastTask = EMPTY;
                    }).start();
                }
            }
        });
    }

    @Override public File getBackupFile(String extension) {
        createRootPath();
        return UniqueFileUtils.buildUniqueFile(getDestPath(), getBackupFileRawName(), extension);
    }

    /**
     * @return Name of file without extension
     */
    private String getBackupFileRawName() {
        String raw;
        if(getConfig().isAutoSeparate()) {
            raw = getConfig().getRawFileNameBackupGroups();
        } else {
            raw = getConfig().getRawFileNameBackup();
        }
        return NameParser.getParsedName(raw, lastTask);
    }

    @Override
    public File getFinalFile(String extension) {
        createRootPath();
        return UniqueFileUtils.buildUniqueFile(getDestPath(), getFinalFileRawName(), extension);
    }

    /**
     * @return Name of file without extension
     */
    private String getFinalFileRawName() {
        String raw;
        if(getConfig().isAutoSeparate()) {
            raw = getConfig().getRawFileNameFinalGroups();
        } else {
            raw = getConfig().getRawFileNameFinal();
        }
        return NameParser.getParsedName(raw, lastTask);
    }

    private void createRootPath() {
        UniqueFileUtils.createPath(getDestPath());
    }

    private String getDestPath() {
        return getConfig().getRecordDestPath();
    }

    private static MeetingTask getEmptyTask() {
        MeetingTask empty = new MeetingTask();
        empty.setType(MeetingTask.Type.NONE);
        empty.setName("Nagranie poza programem");
        return empty;
    }

    private ConfigReadable getConfig() {
        return config;
    }
}
