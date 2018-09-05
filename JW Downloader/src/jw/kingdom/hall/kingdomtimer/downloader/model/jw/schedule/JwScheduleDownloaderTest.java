
/*
 * Created 05.09.18 00:43.
 * Last modified 05.09.18 00:43
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule;

import jw.kingdom.hall.kingdomtimer.downloader.entity.*;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.JwScheduleDownloader;

public class JwScheduleDownloaderTest {
    public static void main(String[] args) {
        testDownloadingTasks();
    }

    private static void testDownloadingTasks() {
        new JwScheduleDownloader().getUrlForToday("ru", url -> {
//            url = "https://wol.jw.org/pl/wol/dt/r12/lp-p/2018/9/4";
//            url = "https://wol.jw.org/en/wol/dt/r1/lp-e/2018/9/5";
//            url = "https://wol.jw.org/en/wol/dt/r1/lp-e";
//            url = "https://wol.jw.org/ru/wol/dt/r2/lp-u/2018/9/5";

            System.out.println("url = " + url);
            ScheduleDownloader.InputData data = new ScheduleDownloader.InputData() {
                @Override
                public boolean isCircuitVisit() {
                    return false;
                }

                @Override
                public String getLangCode() {
                    return "ru";
                }

                @Override
                public String getDestUrl() {
                    return url;
                }

                @Override
                public ScheduleTranslator getTranslator() {
                    return new DefaultScheduleTranslator();
                }
            };
            new JwScheduleDownloader().downloadWeek(data, tasks -> {
                for(ScheduleTask task:tasks) {
                    System.out.println(task.getName()+" ("+task.getTime()+")");
                }
            });
        });
    }
}
