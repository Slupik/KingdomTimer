/*
 * Created 05.09.18 02:42.
 * Last modified 05.09.18 02:40
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

/*
 * Created 05.09.18 02:35.
 * Last modified 05.09.18 02:35
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.entity;

import java.util.List;

public interface ScheduleDownloader {

    /**
     * @param languageCode compatible with the standard ISO 639
     */
    void getUrlForToday(String languageCode, UrlCallback callback);

    void downloadWeek(InputData dat, DownloadCallback callback);

    interface DownloadCallback {
        void onDownload(List<ScheduleTask> tasks);
    }

    interface UrlCallback {
        void onReturnUrl(String url);
    }

    interface InputData {

        boolean isCircuitVisit();

        /**
         * @return language code compatible with the standard ISO 639
         */
        String getLangCode();

        String getDestUrl();

        ScheduleTranslator getTranslator();
    }
}
