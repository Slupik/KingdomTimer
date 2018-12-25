
/*
 * Created 05.09.18 00:43.
 * Last modified 05.09.18 00:43
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule;

import jw.kingdom.hall.kingdomtimer.downloader.entity.DefaultScheduleTranslator;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTranslator;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.JwScheduleDownloader;

public class JwScheduleDownloaderTest {

    private static final boolean CHECK_SPECIFIC_LINK = true;

    public static void main(String[] args) {
        testDownloadingTasks();
    }

    private static void testDownloadingTasks() {
        if(CHECK_SPECIFIC_LINK) {
            new JwScheduleDownloader().downloadWeek(new ScheduleDownloader.InputData() {
                @Override
                public boolean isCircuitVisit() {
                    return false;
                }

                @Override
                public String getLangCode() {
                    return "pl";
                }

                @Override
                public String getDestUrl() {
                    return "https://wol.jw.org/pl/wol/dt/r12/lp-p/2019/1/8";
                }

                @Override
                public ScheduleTranslator getTranslator() {
                    return new DefaultScheduleTranslator();
                }

                @Override
                public int getTimeToEvaluate() {
                    return 60;
                }
            }, tasks -> {
                for (ScheduleTask task : tasks) {
                    System.out.println(task.getName() + " (" + task.getTime() + ") buzzer: "+task.isActiveBuzzer());
                }
            });
        } else {
            new JwScheduleDownloader().getUrlForToday("ru", new ScheduleDownloader.UrlCallback() {
                @Override
                public void onReturnUrl(String url) {
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
                            return "pl";
                        }

                        @Override
                        public String getDestUrl() {
                            return url;
                        }

                        @Override
                        public ScheduleTranslator getTranslator() {
                            return new DefaultScheduleTranslator();
                        }

                        @Override
                        public int getTimeToEvaluate() {
                            return 60;
                        }
                    };
                    new JwScheduleDownloader().downloadWeek(data, tasks -> {
                        for (ScheduleTask task : tasks) {
                            System.out.println(task.getName() + " (" + task.getTime() + ") buzzer: "+task.isActiveBuzzer());
                        }
                    });
                }

                @Override
                public void onConnectionError() {
                    System.out.println("Connection error");
                }
            });
        }
    }
}
