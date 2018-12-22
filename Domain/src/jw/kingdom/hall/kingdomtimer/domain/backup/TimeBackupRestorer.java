package jw.kingdom.hall.kingdomtimer.domain.backup;

import com.google.gson.Gson;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.OfflineMeetingBean;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.TimeBackupBean;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.file.FileManager;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class TimeBackupRestorer {
    private final RecordControl recordControl;
    private final Schedule schedule;
    private final Countdown countdown;
    private final FileManager fileManager;
    private TimeBackupBean data;

    TimeBackupRestorer(RecordControl recordControl, Schedule schedule, Countdown countdown, FileManager fileManager){
        this.recordControl = recordControl;
        this.schedule = schedule;
        this.countdown = countdown;
        this.fileManager = fileManager;
        init();
    }

    private void init() {
        new Thread(()->{
            String content = FileUtils.getContent(fileManager.getScheduleFile());
            if(content.length()>1) {
                data = new Gson().fromJson(content, TimeBackupBean.class);
            }
        }).start();
    }

    boolean isAvailable() {
        return data !=null;
    }

    void restore(){
        if(isAvailable()) {
            restoreSchedule();
            restoreCountdown();
            restoreRecording();
        }
    }

    void delete() {
        fileManager.getScheduleFile().delete();
        fileManager.deleteRootPath();
    }

    private void restoreRecording() {
        if(data.isRecording()) {
            recordControl.start();
        }
    }

    private void restoreSchedule() {
        List<TaskBean> list = new ArrayList<>();
        for(OfflineMeetingBean bean: data.getSchedule()) {
            list.add(bean.convertToMeetingTask());
        }
        schedule.clear();
        schedule.addTask(list);
    }

    private void restoreCountdown() {
        if(data.getBean()!=null) {
            TaskBean task = data.getBean().convertToMeetingTask();
            countdown.start(task);
            if(data.isPause()) {
                countdown.pause();
            }
            countdown.enforceTime(getCalculatedTime(data));
            countdown.addTime(data.getAddedTime());
        } else {
            countdown.stop();
        }
    }

    private static int getCalculatedTime(TimeBackupBean data) {
        int lastTime = data.getLastTime();
        if(data.isPause()) {
            return lastTime;
        } else {
            long lastStartTime = data.getLastStartTime();

            long current = System.currentTimeMillis();
            int difference = (int) (current - lastStartTime)/1000;
            return lastTime-difference;
        }
    }
}
