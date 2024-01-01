
/*
 * Created 05.09.18 00:44.
 * Last modified 05.09.18 00:44
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser.OnlineScheduleParser;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser.v1.ParserFor2023;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Downloader {

    List<ScheduleTask> getTasks(ScheduleDownloader.InputData data, String url) throws IOException {
        List<ScheduleTask> list = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        return getParsers().stream()
                //TODO use real time of URL
                .filter(parser -> parser.supports(LocalDateTime.now()))
                .findAny()
                .map(parser -> parser.parse(data, doc))
                .orElse(new ArrayList<>());
    }

    private List<OnlineScheduleParser> getParsers(){
        return Arrays.asList(new ParserFor2023());
    }

}
