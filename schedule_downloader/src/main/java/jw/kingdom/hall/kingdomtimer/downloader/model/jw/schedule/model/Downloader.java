
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
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser.v2.ParserFor2024;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser.v3.ParserFor2025;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Downloader {

    List<ScheduleTask> getTasks(ScheduleDownloader.InputData data, String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        LocalDate targetDate = parseURL(url);
        return getParsers().stream()
                .filter(parser -> parser.supports(targetDate))
                .findAny()
                .map(parser -> parser.parse(data, doc))
                .orElse(new ArrayList<>());
    }

    private List<OnlineScheduleParser> getParsers(){
        return Arrays.asList(new ParserFor2023(), new ParserFor2024(), new ParserFor2025());
    }

    private static LocalDate parseURL(String url) {
        Pattern pattern = Pattern.compile(".*/(\\d{4})/(\\d+)$");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int week = Integer.parseInt(matcher.group(2));

            return getStartOfWeek(year, week);
        } else {
            return LocalDate.now();
        }
    }

    public static LocalDate getStartOfWeek(int year, int week) {
        return LocalDate.now()
                .withYear(year)
                .with(TemporalAdjusters.firstDayOfYear())
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
                .plusWeeks(week - 1); // Subtract 1 because we start counting weeks from 0
    }

}
