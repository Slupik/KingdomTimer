/*
 * Created 05.09.18 00:40.
 * Last modified 05.09.18 00:40
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

/*
 * Created 05.09.18 00:38.
 * Last modified 05.09.18 00:38
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.jw.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.jw.schedule.entity.JwTask;

import java.io.IOException;
import java.util.List;

public class ScheduleDownloader {

    /**
     * @param languageCode compatible with the standard ISO 639
     */
    public static void getUrlForToday(String languageCode, UrlCallback callback) {
        new Thread(()-> callback.onReturnUrl(new UrlDownloader().getUrl(languageCode))).start();
    }

    /**
     * @param languageCode compatible with the standard ISO 639
     */
    public static void autoSelectAndDownloadWeek(String languageCode, DownloadCallback callback) {
        new Thread(()->{
            try {
                String url = new UrlDownloader().getUrl(languageCode);
                callback.onDownload(new Downloader().getTasks(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void downloadWeek(String url, DownloadCallback callback) {
        new Thread(()->{
            try {
                callback.onDownload(new Downloader().getTasks(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public interface DownloadCallback {
        void onDownload(List<JwTask> tasks);
    }
    public interface UrlCallback {
        void onReturnUrl(String url);
    }
}
