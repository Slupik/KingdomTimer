package jw.kingdom.hall.kingdomtimer.domain.backup;

import jw.kingdom.hall.kingdomtimer.domain.backup.entity.OfflineMeetingBean;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.TimeBackupBean;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdown;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class TimeBackupRestorer {
    static void restore(TimeBackupBean data) {
        restoreSchedule(data);
        restoreCountdown(data);
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
