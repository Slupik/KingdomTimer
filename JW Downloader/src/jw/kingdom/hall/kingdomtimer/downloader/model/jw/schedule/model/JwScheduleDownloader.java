
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

    @Override
    public void downloadWeek(InputData data, DownloadCallback callback) {
        new Thread(() -> {
            try {
                String url;
                if(data.getDestUrl()!=null && data.getDestUrl().length()>0) {
                    url = data.getDestUrl();
                } else {
                    url = new UrlDownloader().getUrl(data.getLangCode());
                }
                callback.onDownload(new Downloader().getTasks(data, url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
