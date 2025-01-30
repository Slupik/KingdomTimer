/*
 * Feel free to use
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class ConnectionCreator {

    public Connection create(String url) {
        return Jsoup.connect(url)
                .method(Connection.Method.GET)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Accept-Language", "pl-PL,pl;q=0.9")
                .header("Priority", "u=0, i")
                .header("Sec-CH-UA", "\"Not A(Brand\";v=\"8\", \"Chromium\";v=\"132\", \"Google Chrome\";v=\"132\"")
                .header("Sec-CH-UA-Mobile", "?0")
                .header("Sec-CH-UA-Platform", "\"Windows\"")
                .header("Sec-Fetch-Dest", "document")
                .header("Sec-Fetch-Mode", "navigate")
                .header("Sec-Fetch-Site", "none")
                .header("Sec-Fetch-User", "?1")
                .header("Upgrade-Insecure-Requests", "1")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36")
                .ignoreContentType(true)
                .followRedirects(true);
    }

}
