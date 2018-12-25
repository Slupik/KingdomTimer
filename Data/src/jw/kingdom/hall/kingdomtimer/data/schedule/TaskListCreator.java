/*
 * Created 05.09.18 03:47.
 * Last modified 05.09.18 03:46
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.data.schedule;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
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
        downloader.downloadWeek(data, new ScheduleDownloader.DownloadCallback() {
            @Override
            public void onDownload(List<ScheduleTask> tasks) {
                List<TaskBean> list = new ArrayList<>();
                for(ScheduleTask scheduleTask:tasks) {
                    list.add(ScheduleTaskToMeetingTaskConverter.getMeetingTask(scheduleTask));
                }
                callback.onDataReceive(list);
            }

            @Override
            public void onConnectionError() {
                callback.onConnectionError();
            }
        });
    }

    static void getWeekendTasks(boolean circuit, Callback callback){
        List<TaskBean> list = new ArrayList<>();

        TaskBean lecture = new TaskBean();
        lecture.setName("Wykład publiczny");
        lecture.setTime(30 * 60);
        lecture.setUseBuzzer(false);
        lecture.setType(TaskBean.Type.LECTURE);
        list.add(lecture);

        TaskBean watchtower = new TaskBean();
        watchtower.setName("Strażnica");
        if(circuit) {
            watchtower.setTime(30 * 60);
        } else {
            watchtower.setTime(60 * 60);
        }
        watchtower.setUseBuzzer(false);
        watchtower.setType(TaskBean.Type.WATCHTOWER);
        list.add(watchtower);


        if(circuit) {
            TaskBean overseerLecture = new TaskBean();
            overseerLecture.setName("Przemówienie nadzorcy obwodu");
            overseerLecture.setTime(30 * 60);
            overseerLecture.setUseBuzzer(false);
            overseerLecture.setType(TaskBean.Type.CIRCUIT);
            list.add(overseerLecture);
        }

        callback.onDataReceive(list);
    }

    interface Callback {
        void onDataReceive(List<TaskBean> list);
        void onConnectionError();
    }
}
