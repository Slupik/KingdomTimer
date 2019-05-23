/*
 * Created 05.09.18 03:47.
 * Last modified 05.09.18 03:21
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.data.schedule;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;

class ScheduleTaskToMeetingTaskConverter {
    static TaskBean getMeetingTask(ScheduleTask schedule) {
        TaskBean meeting = new TaskBean();
        meeting.setName(schedule.getName());
        meeting.setUseBuzzer(schedule.isActiveBuzzer());
        meeting.setTime(schedule.getTime());
        switch (schedule.getType()) {
            case UNKNOWN: {
                meeting.setType(TaskBean.Type.UNKNOWN);
                break;
            }
            case LIVING: {
                meeting.setType(TaskBean.Type.LIVING);
                break;
            }
            case MINISTRY: {
                meeting.setType(TaskBean.Type.MINISTRY);
                break;
            }
            case TREASURES: {
                meeting.setType(TaskBean.Type.TREASURES);
                break;
            }
            case CIRCUIT: {
                meeting.setType(TaskBean.Type.CIRCUIT);
                break;
            }
            default: {
                meeting.setType(TaskBean.Type.UNKNOWN);
                break;
            }
        }
        return meeting;
    }
}
