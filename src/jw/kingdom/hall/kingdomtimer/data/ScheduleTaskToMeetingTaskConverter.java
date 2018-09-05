/*
 * Created 05.09.18 03:11.
 * Last modified 05.09.18 03:11
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.data;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;

class ScheduleTaskToMeetingTaskConverter {
    static MeetingTask getMeetingTask(ScheduleTask schedule) {
        MeetingTask meeting = new MeetingTask();
        meeting.setName(schedule.getName());
        meeting.setTimeInSeconds(schedule.getTime());
        switch (schedule.getType()) {
            case UNKNOWN: {
                meeting.setType(MeetingTask.Type.UNKNOWN);
                break;
            }
            case LIVING: {
                meeting.setType(MeetingTask.Type.LIVING);
                break;
            }
            case MINISTRY: {
                meeting.setType(MeetingTask.Type.MINISTRY);
                break;
            }
            case TREASURES: {
                meeting.setType(MeetingTask.Type.TREASURES);
                break;
            }
            case CIRCUIT: {
                meeting.setType(MeetingTask.Type.CIRCUIT);
                break;
            }
            default: {
                meeting.setType(MeetingTask.Type.UNKNOWN);
                break;
            }
        }
        return meeting;
    }
}
