/*
 * Created 05.09.18 02:44.
 * Last modified 05.09.18 02:44
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.JwScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.model.SbScheduleDownloader;

public class ScheduleDownloaderFacade implements ScheduleDownloader {
    private ScheduleDownloader defaultDownloader = new SbScheduleDownloader();

    @Override
    public void getUrlForToday(String languageCode, UrlCallback callback) {
        defaultDownloader.getUrlForToday(languageCode, callback);
    }

    @Override
    public void autoSelectAndDownloadWeek(String languageCode, boolean circuit, DownloadCallback callback) {
        defaultDownloader.autoSelectAndDownloadWeek(languageCode, circuit, callback);
    }

    @Override
    public void downloadWeek(String url, boolean circuit, DownloadCallback callback) {
        defaultDownloader.downloadWeek(url, circuit, callback);
    }
}
