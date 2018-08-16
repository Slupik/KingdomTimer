package jw.kingdom.hall.kingdomtimer.data.online.model;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
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
