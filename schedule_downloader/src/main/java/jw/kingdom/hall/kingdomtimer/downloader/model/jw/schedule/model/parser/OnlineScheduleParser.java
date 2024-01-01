/*
 * Feel free to use
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import org.jsoup.nodes.Document;

import java.time.LocalDate;
import java.util.List;

public interface OnlineScheduleParser {

    boolean supports(LocalDate date);

    List<ScheduleTask> parse(ScheduleDownloader.InputData data, Document document);

}