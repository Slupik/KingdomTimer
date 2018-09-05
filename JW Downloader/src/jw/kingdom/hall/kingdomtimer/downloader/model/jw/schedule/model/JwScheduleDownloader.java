
/*
 * Created 05.09.18 00:38.
 * Last modified 05.09.18 00:38
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;

import java.io.IOException;

public class JwScheduleDownloader implements ScheduleDownloader {

    /**
     * @param languageCode compatible with the standard ISO 639
     */
    @Override
    public void getUrlForToday(String languageCode, UrlCallback callback) {
        new Thread(() -> callback.onReturnUrl(new UrlDownloader().getUrl(languageCode))).start();
    }

    //TODO implement circuit visit
    /**
     * @param languageCode compatible with the standard ISO 639
     */
    @Override
    public void autoSelectAndDownloadWeek(String languageCode, boolean circuit, DownloadCallback callback) {
        new Thread(() -> {
            try {
                String url = new UrlDownloader().getUrl(languageCode);
                callback.onDownload(new Downloader().getTasks(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void downloadWeek(String url, boolean circuit, DownloadCallback callback) {
        new Thread(() -> {
            try {
                callback.onDownload(new Downloader().getTasks(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
