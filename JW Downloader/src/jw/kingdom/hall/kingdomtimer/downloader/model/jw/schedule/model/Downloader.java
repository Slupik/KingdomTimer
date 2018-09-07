
/*
 * Created 05.09.18 00:44.
 * Last modified 05.09.18 00:44
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Downloader {

    List<ScheduleTask> getTasks(ScheduleDownloader.InputData data, String url) throws IOException {
        List<ScheduleTask> list = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Element partWithSchedule = doc.getElementsByClass("bodyTxt").get(1);

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
            ScheduleTask evaluation = PredefinedTask.getTaskToEvaluate(data.getTranslator(), data.getTimeToEvaluate());
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
                finalList.add(PredefinedTask.getTaskToEvaluate(data.getTranslator(), data.getTimeToEvaluate()));
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
        Elements probablyTasks = source.getElementsByClass("su");
        for(Element probablyTask:probablyTasks) {
            try {
                String s = Jsoup.parse(probablyTask.html()).text();
                ScheduleTask task = RawTaskParser.getParsed(s);
                task.setActiveBuzzer(!RawTaskParser.isContainsVideo(probablyTask));
                list.add(task);
            } catch (WrongElementException ignore) {}
        }
        return list;
    }
}
