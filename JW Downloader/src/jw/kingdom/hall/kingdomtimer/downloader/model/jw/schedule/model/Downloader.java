
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

        list.addAll(getFromTreasures(doc));
        list.addAll(getFromMinistry(doc));
        list.addAll(getFromLiving(data, doc));

        return list;
    }

    private List<ScheduleTask> getFromTreasures(Document doc) {
        Element treasures = doc.getElementById("section2");
        List<ScheduleTask> list = getTasksFromElement(treasures);
        for(ScheduleTask task:list) {
            task.setType(ScheduleTaskType.TREASURES);
            task.setActiveBuzzer(false);
        }
        list.get(list.size()-1).setActiveBuzzer(true);
        return list;
    }

    private List<ScheduleTask> getFromMinistry(Document doc) {
        Element ministry = doc.getElementById("section3");
        List<ScheduleTask> list = getTasksFromElement(ministry);
        for(ScheduleTask task:list) {
            task.setType(ScheduleTaskType.MINISTRY);
        }
        return list;
    }

    private List<ScheduleTask> getFromLiving(ScheduleDownloader.InputData data, Document doc) {
        Element living = doc.getElementById("section4");
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
//        try {
//            Element probablyTask = probablyTasks.get(0);
//            String s = Jsoup.parse(probablyTask.html()).text();
//            ScheduleTask task = RawTaskParser.getParsed(s);
//            task.setActiveBuzzer(RawTaskParser.isContainsVideo(probablyTask));
//            list.add(task);
//        } catch (WrongElementException ignore) {}
        return list;
    }
}
