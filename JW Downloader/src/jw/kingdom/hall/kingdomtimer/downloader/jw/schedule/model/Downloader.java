/*
 * Created 05.09.18 00:44.
 * Last modified 05.09.18 00:44
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.jw.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.jw.schedule.entity.JwTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Downloader {

    List<JwTask> getTasks(String url) throws IOException {
        List<JwTask> list = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();

        list.addAll(getFromTreasures(doc));
        list.addAll(getFromMinistry(doc));
        list.addAll(getFromLiving(doc));

        return list;
    }

    private List<JwTask> getFromTreasures(Document doc) {
        Element treasures = doc.getElementById("section2");
        return getTasksFromElement(treasures);
    }

    private List<JwTask> getFromMinistry(Document doc) {
        Element treasures = doc.getElementById("section3");
        return getTasksFromElement(treasures);
    }

    private List<JwTask> getFromLiving(Document doc) {
        Element treasures = doc.getElementById("section4");
        return getTasksFromElement(treasures);
    }

    private List<JwTask> getTasksFromElement(Element source) {
        List<JwTask> list = new ArrayList<>();
        Elements probablyTasks = source.getElementsByClass("su");
        for(Element probablyTask:probablyTasks) {
            try {
                String s = Jsoup.parse(probablyTask.html()).text();
                JwTask task = RawTaskParser.getParsed(s);
                list.add(task);
            } catch (WrongElementException ignore) {}
        }
        return list;
    }
}
