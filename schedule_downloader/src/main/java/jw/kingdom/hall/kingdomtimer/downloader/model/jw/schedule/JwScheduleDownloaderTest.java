
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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JwScheduleDownloaderTest {

    private static final boolean CHECK_SPECIFIC_LINK = true;

    public static void main(String[] args) {
        testDownloadingTasks();
//        runMassTest();
    }

    private static void runMassTest() {
        IntStream.range(1, 19)
                .forEach(weekIndex -> {
                    try {
                        checkWeek(weekIndex);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("FATAL ERROR for week " + weekIndex);
                    }
                });
    }

    private static void checkWeek(int weekIndex) {
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
                return "https://wol.jw.org/pl/wol/meetings/r12/lp-p/2024/" + weekIndex;
            }

            @Override
            public ScheduleTranslator getTranslator() {
                return new DefaultScheduleTranslator();
            }

            @Override
            public int getTimeToEvaluate() {
                return 60;
            }
        }, new ScheduleDownloader.DownloadCallback() {
            @Override
            public void onDownload(List<ScheduleTask> tasks) {
                int amountOfSections = tasks.stream()
                        .map(ScheduleTask::getType)
                        .collect(Collectors.toSet())
                        .size();
                if (amountOfSections != 3) {
                    System.out.println("ERROR for week " + weekIndex + ", detected only " + amountOfSections + "sections");
                }
                boolean allHasAssignedTime = tasks.stream()
                        .map(ScheduleTask::getTime)
                        .allMatch(time -> time > 0);
                if (!allHasAssignedTime) {
                    System.out.println("ERROR for week " + weekIndex + ", detected item without time");
                }
            }

            @Override
            public void onConnectionError() {
                System.err.println("Conn error (week " + weekIndex + ")");
            }
        });
    }

    private static void testDownloadingTasks() {
        if (CHECK_SPECIFIC_LINK) {
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
                    return "https://wol.jw.org/pl/wol/meetings/r12/lp-p/2025/1";
                }

                @Override
                public ScheduleTranslator getTranslator() {
                    return new DefaultScheduleTranslator();
                }

                @Override
                public int getTimeToEvaluate() {
                    return 60;
                }
            }, new ScheduleDownloader.DownloadCallback() {
                @Override
                public void onDownload(List<ScheduleTask> tasks) {
                    if (tasks.isEmpty()) {
                        System.err.println("No tasks in the list");
                    }
                    for (ScheduleTask task : tasks) {
                        System.out.println(task.getName() + " (" + task.getTime() + ") buzzer: " + task.isActiveBuzzer());
                    }
                }

                @Override
                public void onConnectionError() {
                    System.err.println("Conn error");
                }
            });
        } else {
            new JwScheduleDownloader().getUrlForToday("pl", new ScheduleDownloader.UrlCallback() {
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
                    new JwScheduleDownloader().downloadWeek(data, new ScheduleDownloader.DownloadCallback() {
                        @Override
                        public void onDownload(List<ScheduleTask> tasks) {
                            for (ScheduleTask task : tasks) {
                                System.out.println(task.getName() + " (" + task.getTime() + ") buzzer: " + task.isActiveBuzzer());
                            }
                        }

                        @Override
                        public void onConnectionError() {
                            System.err.println("Conn error");
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
