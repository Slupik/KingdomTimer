/*
 * Feel free to use
 */

/*
 * Feel free to use
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser.v1;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.PredefinedTask;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.WrongElementException;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser.OnlineScheduleParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParserFor2023 implements OnlineScheduleParser {

    @Override
    public boolean supports(LocalDate date) {
        return date.getYear() == 2023;
    }

    @Override
    public List<ScheduleTask> parse(ScheduleDownloader.InputData data, Document doc) {
        Element partWithSchedule = doc.getElementsByClass("bodyTxt").get(0);

        List<ScheduleTask> list = new ArrayList<>();
        list.addAll(getOpeningComments(partWithSchedule));
        list.addAll(getFromTreasures(partWithSchedule, data));
        list.addAll(getFromMinistry(partWithSchedule, data));
        list.addAll(getFromLiving(data, partWithSchedule));
        return list;
    }

    private List<ScheduleTask> getOpeningComments(Element element) {
        Element treasures = element.getElementById("section1");
        List<ScheduleTask> list = getTasksFromElement(treasures);
        for(ScheduleTask task:list) {
            task.setType(ScheduleTaskType.TREASURES);
            task.setActiveBuzzer(false);
        }
        return list;
    }

    private List<ScheduleTask> getFromTreasures(Element element, ScheduleDownloader.InputData data) {
        Element treasures = element.getElementById("section2");
        List<ScheduleTask> list = getTasksFromElement(treasures);
        for(ScheduleTask task:list) {
            task.setType(ScheduleTaskType.TREASURES);
            task.setActiveBuzzer(false);
        }
        list.get(list.size()-1).setActiveBuzzer(true);
        if(data.getTimeToEvaluate()>=0) {
            ScheduleTask evaluation = PredefinedTask.getTaskToEvaluate(data.getTranslator(),
                    data.getTimeToEvaluate(),
                    ScheduleTaskType.TREASURES);
            evaluation.setType(ScheduleTaskType.TREASURES);
            list.add(evaluation);
        }
        return list;
    }

    private List<ScheduleTask> getFromMinistry(Element element, ScheduleDownloader.InputData data) {
        Element ministry = element.getElementById("section3");
        List<ScheduleTask> list = getTasksFromElement(ministry);
        List<ScheduleTask> finalList = new ArrayList<>();
        for(ScheduleTask task:list) {
            task.setType(ScheduleTaskType.MINISTRY);
            finalList.add(task);
            if(task.isActiveBuzzer() && data.getTimeToEvaluate()>=0) {
                finalList.add(PredefinedTask.getTaskToEvaluate(data.getTranslator(),
                        data.getTimeToEvaluate(),
                        ScheduleTaskType.MINISTRY));
            }
        }
        return finalList;
    }

    private List<ScheduleTask> getFromLiving(ScheduleDownloader.InputData data, Element element) {
        Element living = element.getElementById("section4");
        List<ScheduleTask> list = getTasksFromElement(living);
        for(ScheduleTask task:list) {
            task.setType(ScheduleTaskType.LIVING);
            task.setActiveBuzzer(false);
        }
        if(data.isCircuitVisit()) {
            list.remove(list.size()-2); //remove penultimate
            list.add(PredefinedTask.getCircuitLecture(data.getTranslator()));
        }
        return list;
    }

    private List<ScheduleTask> getTasksFromElement(Element source) {
        List<ScheduleTask> list = new ArrayList<>();
        Elements probablyTasks = source.getElementsByClass("so");
        for(Element probablyTask:probablyTasks) {
            try {
                String s = Jsoup.parse(probablyTask.html()).text();
                ScheduleTask task = RawTaskParser.getParsed(s);

                if(task.getTime()<10*60) {//Really long tasks shouldn't have a buzzer
                    task.setActiveBuzzer(!RawTaskParser.isContainsVideo(probablyTask));
                }

                list.add(task);
            } catch (WrongElementException ignore) {}
        }
        return list;
    }
}
