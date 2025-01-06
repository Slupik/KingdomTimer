/*
 * Feel free to use
 */

/*
 * Feel free to use
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser.v3;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleDownloader;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.PredefinedTask;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.parser.OnlineScheduleParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserFor2025 implements OnlineScheduleParser {

    private static final List<ScheduleTaskType> TASK_TYPE_FOR_INDEX = new ArrayList<ScheduleTaskType>() {{
        add(ScheduleTaskType.TREASURES);
        add(ScheduleTaskType.TREASURES);
        add(ScheduleTaskType.MINISTRY);
        add(ScheduleTaskType.LIVING);
    }};

    @Override
    public boolean supports(LocalDate date) {
        return date.getYear() == 2025;
    }

    @Override
    public List<ScheduleTask> parse(ScheduleDownloader.InputData data, Document doc) {
        Element partWithSchedule = doc.getElementsByClass("bodyTxt").get(1);
        List<ScheduleTask> list = new ArrayList<>();

        Elements children = partWithSchedule.children();
        int part = 0;
        for (int i = 0; i < children.size(); i++) {
            Element child = children.get(i);
            String tagName = child.tagName();
            if (isNewSection(child, tagName, children)) {
                part++;
            } else if (Objects.equals(tagName, "h3")) {
                if (list.size() == 0) {
                    list.add(parseOpeningComments(child));
                } else if (i == children.size() - 1) {
                    list.add(parseClosingComments(child));
                } else {
                    Element nextChild = children.get(i + 1);
                    if (!Objects.equals(nextChild.tagName(), "div")) {
                        // it's probably a song
                        continue;
                    }
                    list.addAll(parseTask(child, nextChild, TASK_TYPE_FOR_INDEX.get(part), data));
                }
            } else if (Objects.equals(tagName, "div") && child.getElementsByTag("h3").size() != 0) {
                list.addAll(parseNestedTask(child, TASK_TYPE_FOR_INDEX.get(part), data));
            }
        }

        if (data.isCircuitVisit()) {
            list.remove(list.size() - 2);
            list.add(getCircuitLectureTask(data));
        }

        return list;
    }

    private boolean isNewSection(Element child, String tagName, Elements children) {
        if (Objects.equals(tagName, "h2")) {
            return true;
        }
        boolean containsAnyImmediateHeader = child.children().stream()
                .map(Element::tagName)
                .anyMatch("h2"::equalsIgnoreCase);
        if (Objects.equals(tagName, "div") && containsAnyImmediateHeader) {
            return true;
        }
        return false;
    }

    private List<ScheduleTask> parseNestedTask(Element element, ScheduleTaskType taskType, ScheduleDownloader.InputData data) {
        Elements children = element.children();
        for (int i = 0; i < children.size(); i++) {
            Element child = children.get(i);
            String tagName = child.tagName();
            if (Objects.equals(tagName, "h3")) {
                return parseTask(child, children.get(i + 1), taskType, data);
            }
        }
        return Collections.emptyList();
    }

    private List<ScheduleTask> parseTask(Element child, Element element, ScheduleTaskType taskType, ScheduleDownloader.InputData data) {
        int time = extractMinutes(element.text());
        boolean isExercise = isExercise(taskType, time);

        ScheduleTask task = new ScheduleTask();
        task.setType(taskType);
        task.setActiveBuzzer(isExercise);
        task.setTime(time);
        task.setName(child.text());

        List<ScheduleTask> list = new ArrayList<>();
        list.add(task);
        if (isExercise && data.getTimeToEvaluate() > 0) {
            list.add(getEvaluationTask(data, task.getType()));
        }

        return list;
    }

    private ScheduleTask getEvaluationTask(ScheduleDownloader.InputData data, ScheduleTaskType type) {
        return PredefinedTask.getTaskToEvaluate(data.getTranslator(), data.getTimeToEvaluate(), type);
    }

    private ScheduleTask getCircuitLectureTask(ScheduleDownloader.InputData data) {
        return PredefinedTask.getCircuitLecture(data.getTranslator());
    }

    private boolean isExercise(ScheduleTaskType taskType, int time) {
        if (!Arrays.asList(ScheduleTaskType.MINISTRY, ScheduleTaskType.TREASURES).contains(taskType)) {
            return false;
        }
        // If short, it's probably a exercise
        return time <= 5 * 60;
    }

    private ScheduleTask parseOpeningComments(Element element) {
        ScheduleTask task = new ScheduleTask();
        task.setType(ScheduleTaskType.TREASURES);
        task.setActiveBuzzer(false);
        task.setTime(extractMinutes(element.children().get(2).text()));
        task.setName(extractTextAfterPipe(element.child(1).text()));
        return task;
    }

    private ScheduleTask parseClosingComments(Element element) {
        ScheduleTask task = new ScheduleTask();
        task.setType(ScheduleTaskType.LIVING);
        task.setActiveBuzzer(false);
        task.setTime(extractMinutes(element.child(1).text()));
        task.setName(element.child(0).text());
        return task;
    }

    private static int extractMinutes(String text) {
        Pattern pattern = Pattern.compile("\\((\\d+) ([a-zA-Zа-яА-Яє-їЄ-Ї.]+)\\)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)) * 60;
        }

        return 0;
    }

    public static String extractTextAfterPipe(String text) {
        String[] parts = text.split("\\|", 2);

        if (parts.length > 1) {
            return parts[1].trim();
        } else {
            return text;
        }
    }

}
