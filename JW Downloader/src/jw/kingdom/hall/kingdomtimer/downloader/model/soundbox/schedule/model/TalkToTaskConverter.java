/*
 * Created 05.09.18 02:53.
 * Last modified 05.09.18 02:53
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.entity.Talk;

class TalkToTaskConverter {
    static ScheduleTask getConverted(Talk talk) {
        ScheduleTask task = new ScheduleTask();
        task.setName(talk.getName());
        task.setTime(talk.getMinutes()*60);
        task.setActiveBuzzer(talk.isStudentTalk());
        return task;
    }
}
