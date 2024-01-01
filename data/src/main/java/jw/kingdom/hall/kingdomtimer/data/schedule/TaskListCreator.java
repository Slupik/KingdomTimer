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
import java.util.Locale;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class TaskListCreator {
    private static final ScheduleDownloader downloader = new ScheduleDownloaderFacade();

    static void getWeekTasks(Config config, boolean circuit, Callback callback) {
        ScheduleDownloaderInputBean data = new ScheduleDownloaderInputBean();
        data.setCircuitVisit(circuit);
        data.setLangCode(getLandCode());
        data.setTranslator(new ScheduleTranslator());
        data.setTimeToEvaluate(config.getTimeToEvaluate());

        //For tests purposes
        //2023
//        data.setDestUrl("https://wol.jw.org/pl/wol/meetings/r12/lp-p/2023/51");
        //2024
//        data.setDestUrl("https://wol.jw.org/pl/wol/meetings/r12/lp-p/2024/02");
//        data.setDestUrl("https://wol.jw.org/uk/wol/meetings/r15/lp-k/2024/1");
//        data.setDestUrl("https://wol.jw.org/pl/wol/meetings/r12/lp-p/2024/04");

        downloader.downloadWeek(data, new ScheduleDownloader.DownloadCallback() {
            @Override
            public void onDownload(List<ScheduleTask> tasks) {
                List<TaskBean> list = new ArrayList<>();
                for (ScheduleTask scheduleTask : tasks) {
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

    private static String getLandCode() {
        return Locale.getDefault().getLanguage();
    }

    static void getWeekendTasks(boolean circuit, Callback callback) {
        callback.onDataReceive(StaticTasksProvider.getForWeekend(circuit));
    }

    interface Callback {
        void onDataReceive(List<TaskBean> list);

        void onConnectionError();
    }
}
