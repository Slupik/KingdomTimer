/*
 * Created 05.09.18 02:38.
 * Last modified 03.09.18 21:14
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

/*
 * Created 05.09.18 02:35.
 * Last modified 03.09.18 21:14
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

/*
 * Created 05.09.18 02:34.
 * Last modified 03.09.18 21:14
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.data.online;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.entity.Talk;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SoundboxTalkConverter {
    public static MeetingTask toMeetingTask(Talk talk){
        MeetingTask task = new MeetingTask();
        task.setTimeInSeconds(talk.getMinutes()*60);
        task.setName(talk.getName());
        task.setUseBuzzer(talk.isStudentTalk());
        return task;
    }
}
