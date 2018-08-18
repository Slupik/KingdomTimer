package jw.kingdom.hall.kingdomtimer.data;

import jw.kingdom.hall.kingdomtimer.data.online.StaticServer;
import jw.kingdom.hall.kingdomtimer.data.online.WebPageUtils;
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
    public static List<MeetingTask> getWeekTasks(){
        List<MeetingTask> list = new ArrayList<>();
        list.add(PredefinedMeetingTask.getPreview());
        list.add(PredefinedMeetingTask.getBibleAnalyst());
        list.add(PredefinedMeetingTask.getBibleTreasure());
        list.add(PredefinedMeetingTask.getBibleReading());
        Meeting meeting = getWeekMeeting();
        if(meeting!=null) {
            for(Talk talk:meeting.getTalks()) {
                list.add(TalkConverter.toMeetingTask(talk));
            }
        }
        list.add(PredefinedMeetingTask.getBookAnalyst());
        list.add(PredefinedMeetingTask.getRepeat());
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

    public static List<MeetingTask> getWeekendTasks(){
        List<MeetingTask> list = new ArrayList<>();

        MeetingTask lecture = new MeetingTask();
        lecture.setName("Wykład biblijny");
        lecture.setTimeInSeconds(30 * 60);
        lecture.setUseBuzzer(false);
        list.add(lecture);

        MeetingTask watchtower = new MeetingTask();
        watchtower.setName("Strażnica");
        watchtower.setTimeInSeconds(60 * 60);
        watchtower.setUseBuzzer(false);
        list.add(watchtower);

        return list;
    }
}
