/*
 * Created 05.09.18 03:47.
 * Last modified 05.09.18 03:46
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.data.schedule;

import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloaderInputBean;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.model.ScheduleDownloaderFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class PredefinedTaskList {
    private static final ScheduleDownloader downloader = new ScheduleDownloaderFacade();

    public static void getWeekTasks(boolean circuit, Callback callback){
        ScheduleDownloaderInputBean data = new ScheduleDownloaderInputBean();
        data.setCircuitVisit(circuit);
        data.setLangCode("pl");
        data.setTranslator(new ScheduleTranslator());
        data.setTimeToEvaluate(AppConfig.getInstance().getTimeToEvaluate());
        downloader.downloadWeek(data, tasks -> {
            List<MeetingTask> list = new ArrayList<>();
            for(ScheduleTask scheduleTask:tasks) {
                list.add(ScheduleTaskToMeetingTaskConverter.getMeetingTask(scheduleTask));
            }
            callback.onDataReceive(list);
        });
    }

    public static void getWeekendTasks(boolean circuit, Callback callback){
        List<MeetingTask> list = new ArrayList<>();

        MeetingTask lecture = new MeetingTask();
        lecture.setName("Wykład publiczny");
        lecture.setTimeInSeconds(30 * 60);
        lecture.setUseBuzzer(false);
        lecture.setType(MeetingTask.Type.LECTURE);
        list.add(lecture);

        MeetingTask watchtower = new MeetingTask();
        watchtower.setName("Strażnica");
        if(circuit) {
            watchtower.setTimeInSeconds(30 * 60);
        } else {
            watchtower.setTimeInSeconds(60 * 60);
        }
        watchtower.setUseBuzzer(false);
        watchtower.setType(MeetingTask.Type.WATCHTOWER);
        list.add(watchtower);


        if(circuit) {
            MeetingTask overseerLecture = new MeetingTask();
            overseerLecture.setName("Przemówienie nadzorcy obwodu");
            overseerLecture.setTimeInSeconds(30 * 60);
            overseerLecture.setUseBuzzer(false);
            overseerLecture.setType(MeetingTask.Type.CIRCUIT);
            list.add(overseerLecture);
        }

        callback.onDataReceive(list);
    }

    public interface Callback {
        void onDataReceive(List<MeetingTask> list);
    }
}
