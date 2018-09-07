package jw.kingdom.hall.kingdomtimer.domain.backup;

import com.google.gson.Gson;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.OfflineMeetingBean;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.TimeBackupBean;
import jw.kingdom.hall.kingdomtimer.domain.time.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;
import jw.kingdom.hall.kingdomtimer.domain.time.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.model.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class TimeBackupRestorer {
    private TimeBackupBean bean;

    TimeBackupRestorer(){
        init();
    }

    private void init() {
        new Thread(()->{
            String content = FileUtils.getContent(FileManager.getScheduleFile());
            if(content.length()>1) {
                bean = new Gson().fromJson(content, TimeBackupBean.class);
            }
        }).start();
    }

    boolean isAvailable() {
        return bean!=null;
    }

    void restore(){
        if(isAvailable()) {
            restore(bean);
        }
    }

    void delete() {
        FileManager.getScheduleFile().delete();
        FileManager.deleteRootPath();
    }

    private static void restore(TimeBackupBean data) {
        restoreSchedule(data);
        restoreCountdown(data);
        restoreRecording(data);
    }

    private static void restoreRecording(TimeBackupBean data) {
        if(data.isRecording()) {
            VoiceRecorder.getInstance().start();
        }
    }

    private static void restoreSchedule(TimeBackupBean data) {
        List<MeetingTask> list = new ArrayList<>();
        for(OfflineMeetingBean bean:data.getSchedule()) {
            list.add(bean.convertToMeetingTask());
        }
        MeetingSchedule.getInstance().clear();
        MeetingSchedule.getInstance().addTask(list);
    }

    private static void restoreCountdown(TimeBackupBean data) {
        if(data.getBean()!=null) {
            MeetingTask task = data.getBean().convertToMeetingTask();
            TimerCountdown.getInstance().start(task);
            if(data.isPause()) {
                TimerCountdown.getInstance().pause();
            }
            TimerCountdown.getInstance().enforceTime(getCalculatedTime(data));
            TimerCountdown.getInstance().addTime(data.getAddedTime());
        } else {
            TimerCountdown.getInstance().stop();
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
