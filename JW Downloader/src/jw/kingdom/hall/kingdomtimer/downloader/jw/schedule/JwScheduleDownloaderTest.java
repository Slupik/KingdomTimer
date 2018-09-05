/*
 * Created 05.09.18 00:43.
 * Last modified 05.09.18 00:43
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.jw.schedule;

import jw.kingdom.hall.kingdomtimer.downloader.jw.schedule.entity.JwTask;
import jw.kingdom.hall.kingdomtimer.downloader.jw.schedule.model.ScheduleDownloader;

public class JwScheduleDownloaderTest {
    public static void main(String[] args) {
//        testDownloadingTasks();
        testDownloadingTasksWithAutoSelect();
    }

    private static void testDownloadingTasksWithAutoSelect() {
        ScheduleDownloader.autoSelectAndDownloadWeek("pl", tasks -> {
            for(JwTask task:tasks) {
                System.out.println(task.getName()+" ("+task.getTime()+")");
            }
        });
    }

    private static void testDownloadingTasks() {
        ScheduleDownloader.getUrlForToday("ru", url -> {
//            url = "https://wol.jw.org/pl/wol/dt/r12/lp-p/2018/9/4";
//            url = "https://wol.jw.org/en/wol/dt/r1/lp-e/2018/9/5";
//            url = "https://wol.jw.org/en/wol/dt/r1/lp-e";
//            url = "https://wol.jw.org/ru/wol/dt/r2/lp-u/2018/9/5";

            System.out.println("url = " + url);
            ScheduleDownloader.downloadWeek(url, tasks -> {
                for(JwTask task:tasks) {
                    System.out.println(task.getName()+" ("+task.getTime()+")");
                }
            });
        });
    }
}
