package jw.kingdom.hall.kingdomtimer.data;

import jw.kingdom.hall.kingdomtimer.data.online.StaticServer;
import jw.kingdom.hall.kingdomtimer.data.online.model.Meeting;
import jw.kingdom.hall.kingdomtimer.data.online.model.Talk;
import jw.kingdom.hall.kingdomtimer.data.online.model.TalkConverter;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class PredefinedTaskList {
    public static List<MeetingTask> getWeekTasks(boolean overseer){
        List<MeetingTask> list = new ArrayList<>();
        list.add(PredefinedMeetingTask.getPreview());
        list.add(PredefinedMeetingTask.getBibleAnalyst());
        list.add(PredefinedMeetingTask.getBibleTreasure());
        list.add(PredefinedMeetingTask.getBibleReading());
        Meeting meeting = getWeekMeeting();
        if(meeting!=null) {
            int i=0;
            for(Talk talk:meeting.getTalks()) {
                MeetingTask task = TalkConverter.toMeetingTask(talk);
                if(i<3) {
                    task.setType(MeetingTask.Type.MINISTRY);
                } else {
                    task.setType(MeetingTask.Type.LIVING);
                }
                list.add(task);
                i++;
            }
        }
        if(!overseer) {
            list.add(PredefinedMeetingTask.getBookAnalyst());
        }
        list.add(PredefinedMeetingTask.getRepeat());
        if(overseer) {
            list.add(PredefinedMeetingTask.getOverseerLecture());
        }
        return list;
    }

    private static Meeting getWeekMeeting() {
        List<Meeting> meetings = StaticServer.getMeetings();

        int weekOfYear = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        for(Meeting meeting:meetings) {
            if(weekOfYear == meeting.getWeekOfYear() && meeting.isWeekMeeting()) {
                return meeting;
            }
        }
        return null;
    }

    public static List<MeetingTask> getWeekendTasks(boolean overseer){
        List<MeetingTask> list = new ArrayList<>();

        MeetingTask lecture = new MeetingTask();
        lecture.setName("Wykład biblijny");
        lecture.setTimeInSeconds(30 * 60);
        lecture.setUseBuzzer(false);
        lecture.setType(MeetingTask.Type.LECTURE);
        list.add(lecture);

        MeetingTask watchtower = new MeetingTask();
        watchtower.setName("Strażnica");
        if(overseer) {
            watchtower.setTimeInSeconds(60 * 60);
        } else {
            watchtower.setTimeInSeconds(30 * 60);
        }
        watchtower.setUseBuzzer(false);
        watchtower.setType(MeetingTask.Type.WATCHTOWER);
        list.add(watchtower);


        if(overseer) {
            MeetingTask overseerLecture = new MeetingTask();
            overseerLecture.setName("Przemówienie podróżującego");
            overseerLecture.setTimeInSeconds(30 * 60);
            overseerLecture.setUseBuzzer(false);
            overseerLecture.setType(MeetingTask.Type.OVERSEER);
            list.add(overseerLecture);
        }

        return list;
    }
}
