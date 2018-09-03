package jw.kingdom.hall.kingdomtimer.data.online.model;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TalkConverter {
    public static MeetingTask toMeetingTask(Talk talk){
        MeetingTask task = new MeetingTask();
        task.setTimeInSeconds(talk.getMinutes()*60);
        task.setName(talk.getName());
        task.setUseBuzzer(talk.isStudentTalk());
        return task;
    }
}
