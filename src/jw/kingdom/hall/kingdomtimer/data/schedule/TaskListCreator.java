/*
 * Created 05.09.18 03:47.
 * Last modified 05.09.18 03:46
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.data.schedule;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
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
class TaskListCreator {
    private static final ScheduleDownloader downloader = new ScheduleDownloaderFacade();

    static void getWeekTasks(Config config, boolean circuit, Callback callback){
        ScheduleDownloaderInputBean data = new ScheduleDownloaderInputBean();
        data.setCircuitVisit(circuit);
        data.setLangCode("pl");
        data.setTranslator(new ScheduleTranslator());
        data.setTimeToEvaluate(config.getTimeToEvaluate());
        downloader.downloadWeek(data, tasks -> {
            List<MeetingTask> list = new ArrayList<>();
            for(ScheduleTask scheduleTask:tasks) {
                list.add(ScheduleTaskToMeetingTaskConverter.getMeetingTask(scheduleTask));
            }
            callback.onDataReceive(list);
        });
    }

    static void getWeekendTasks(boolean circuit, Callback callback){
        List<MeetingTask> list = new ArrayList<>();

        MeetingTask lecture = new MeetingTask();
        lecture.setName("Wykład publiczny");
        lecture.setTime(30 * 60);
        lecture.setUseBuzzer(false);
        lecture.setType(MeetingTask.Type.LECTURE);
        list.add(lecture);

        MeetingTask watchtower = new MeetingTask();
        watchtower.setName("Strażnica");
        if(circuit) {
            watchtower.setTime(30 * 60);
        } else {
            watchtower.setTime(60 * 60);
        }
        watchtower.setUseBuzzer(false);
        watchtower.setType(MeetingTask.Type.WATCHTOWER);
        list.add(watchtower);


        if(circuit) {
            MeetingTask overseerLecture = new MeetingTask();
            overseerLecture.setName("Przemówienie nadzorcy obwodu");
            overseerLecture.setTime(30 * 60);
            overseerLecture.setUseBuzzer(false);
            overseerLecture.setType(MeetingTask.Type.CIRCUIT);
            list.add(overseerLecture);
        }

        callback.onDataReceive(list);
    }

    @FunctionalInterface
    interface Callback {
        void onDataReceive(List<MeetingTask> list);
    }
}
