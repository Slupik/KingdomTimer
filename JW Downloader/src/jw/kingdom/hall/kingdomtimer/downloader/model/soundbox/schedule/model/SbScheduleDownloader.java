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

    @Override
    public void downloadWeek(InputData data, DownloadCallback callback) {
        new Thread(() -> {
            Meeting meeting = getWeekMeeting(getRightUrl(data));
            List<ScheduleTask> list = ScheduleCreator.getAllWeekTasks(data.isCircuitVisit(), meeting);
            callback.onDownload(list);
        }).start();
    }

    protected String getRightUrl(InputData data){
        if(data.getDestUrl()!=null && data.getDestUrl().length()>0) {
            return data.getDestUrl();
        } else {
            return URL;
        }
    }

    private static Meeting getWeekMeeting(String url) {
        List<Meeting> meetings = StaticServer.getMeetings(url);

        int weekOfYear = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        for(Meeting meeting:meetings) {
            if(weekOfYear == meeting.getWeekOfYear() && meeting.isWeekMeeting()) {
                return meeting;
            }
        }
        return null;
    }
}
