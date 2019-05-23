
/*
 * Created 05.09.18 01:43.
 * Last modified 05.09.18 01:43
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.UnknownHostException;

class UrlDownloader {

    private static final String BASE_URL = "https://wol.jw.org";

    /**
     *
     * @param languageCode compatible with the standard ISO 639
     * @return url with meeting schedule
     */
    String getUrl(String languageCode) throws UnknownHostException {
        try {
            String startUrl = BASE_URL+"/"+languageCode;
            Document doc = Jsoup.connect(startUrl).get();
            return BASE_URL + getUrlEnd(doc);
        } catch (UnknownHostException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getUrlEnd(Document doc) {
        Elements allTodayNavElements = doc.getElementsByClass("todayNav");
        if(allTodayNavElements.size()>0) {
            Element dailyTextButton = allTodayNavElements.get(0);
            if(dailyTextButton!=null) {
                Attributes attributes = dailyTextButton.attributes();
                if(attributes.hasKey("href")) {
                    return attributes.get("href");
                }
            }
        }
        return "";
    }
}
