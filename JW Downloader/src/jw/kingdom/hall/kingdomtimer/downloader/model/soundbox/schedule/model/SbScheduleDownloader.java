/*
 * Created 05.09.18 02:47.
 * Last modified 05.09.18 02:42
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.entity.Meeting;

import java.util.Calendar;
import java.util.List;

public class SbScheduleDownloader implements ScheduleDownloader {
    private static final String URL = "https://soundbox.blob.core.windows.net/meeting-feeds/feed.json";

    /**
     * @param languageCode compatible with the standard ISO 639
     */
    @Override
    public void getUrlForToday(String languageCode, UrlCallback callback) {
        new Thread(() -> callback.onReturnUrl(URL)).start();
    }

    /**
     * @param languageCode compatible with the standard ISO 639
     */
    @Override
    public void autoSelectAndDownloadWeek(String languageCode, boolean circuit, DownloadCallback callback) {
        downloadWeek(URL, circuit, callback);
    }

    @Override
    public void downloadWeek(String url, boolean circuit, DownloadCallback callback) {
        new Thread(() -> {
            Meeting meeting = getWeekMeeting();
            List<ScheduleTask> list = ScheduleCreator.getAllWeekTasks(circuit, meeting);
            callback.onDownload(list);
        }).start();
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
}
