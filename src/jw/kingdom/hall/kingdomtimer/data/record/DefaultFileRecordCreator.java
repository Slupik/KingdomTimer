package jw.kingdom.hall.kingdomtimer.data.record;

import jw.kingdom.hall.kingdomtimer.data.UniqueFileUtils;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;

import java.io.File;

/**
 * All rights reserved & copyright ©
 */
public class DefaultFileRecordCreator implements FileRecordCreator {
    private static final MeetingTask EMPTY = getEmptyTask();
    private MeetingTask lastTask = EMPTY;

    public DefaultFileRecordCreator(){
        TimerCountdown.getInstance().addListener(new TimerCountdownListener() {
            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                lastTask = task;
            }

            @Override
            public void onStop() {
                super.onStop();
                if(MeetingSchedule.getInstance().getList().size()==0){
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

    @Override
    public File getBackupFile(String extension) {
        createRootPath();
        return UniqueFileUtils.buildUniqueFile(getDestPath(), getBackupFileRawName(), extension);
    }

    /**
     * @return Name of file without extension
     */
    private String getBackupFileRawName() {
        String raw;
        if(AppConfig.getInstance().isAutoSeparate()) {
            raw = "BACKUP_{dluga_data}_{blok}";
        } else {
            raw = "BACKUP_{dluga_data}";
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
        if(AppConfig.getInstance().isAutoSeparate()) {
            raw = "{data}_{blok}";
        } else {
            raw = "{data}";
        }
        return NameParser.getParsedName(raw, lastTask);
    }

    private void createRootPath() {
        UniqueFileUtils.createPath(getDestPath());
    }

    private String getDestPath() {
        return AppConfig.getInstance().getRecordDestPath();
    }

    private static MeetingTask getEmptyTask() {
        MeetingTask empty = new MeetingTask();
        empty.setType(MeetingTask.Type.NONE);
        empty.setName("Nagranie poza programem");
        return empty;
    }
}
