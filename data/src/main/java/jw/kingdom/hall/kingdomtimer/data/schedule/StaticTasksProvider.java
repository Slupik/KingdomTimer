/*
 * Feel free to use
 */

package jw.kingdom.hall.kingdomtimer.data.schedule;

import jw.kingdom.hall.kingdomtimer.domain.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType;
import jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model.PredefinedTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class StaticTasksProvider {

    static List<TaskBean> getForWeek(AppConfig config, boolean circuit) {
        List<TaskBean> list = new ArrayList<>();

        TaskBean task1 = new TaskBean();
        task1.setName("Uwagi wstępne");
        task1.setTime(60);
        task1.setUseBuzzer(false);
        task1.setType(TaskBean.Type.TREASURES);
        list.add(task1);

        TaskBean task2 = new TaskBean();
        task2.setName("Przemówienie");
        task2.setTime(10 * 60);
        task2.setUseBuzzer(false);
        task2.setType(TaskBean.Type.TREASURES);
        list.add(task2);

        TaskBean task3 = new TaskBean();
        task3.setName("Wyszukujemy duchowe skarby");
        task3.setTime(10 * 60);
        task3.setUseBuzzer(false);
        task3.setType(TaskBean.Type.TREASURES);
        list.add(task3);

        TaskBean task4 = new TaskBean();
        task4.setName("Czytanie Biblii");
        task4.setTime(4 * 60);
        task4.setUseBuzzer(true);
        task4.setType(TaskBean.Type.TREASURES);
        list.add(task4);
        getTaskToEvaluate(config, ScheduleTaskType.TREASURES).ifPresent(list::add);

        TaskBean task5 = new TaskBean();
        task5.setName("Punkt ćwiczebny 1");
        task5.setTime(3 * 60);
        task5.setUseBuzzer(true);
        task5.setType(TaskBean.Type.MINISTRY);
        list.add(task5);
        getTaskToEvaluate(config, ScheduleTaskType.MINISTRY).ifPresent(list::add);

        TaskBean task6 = new TaskBean();
        task6.setName("Punkt ćwiczebny 2");
        task6.setTime(4 * 60);
        task6.setUseBuzzer(true);
        task6.setType(TaskBean.Type.MINISTRY);
        list.add(task6);
        getTaskToEvaluate(config, ScheduleTaskType.MINISTRY).ifPresent(list::add);

        TaskBean task7 = new TaskBean();
        task7.setName("Punkt ćwiczebny 3");
        task7.setTime(5 * 60);
        task7.setUseBuzzer(true);
        task7.setType(TaskBean.Type.MINISTRY);
        list.add(task7);
        getTaskToEvaluate(config, ScheduleTaskType.MINISTRY).ifPresent(list::add);

        TaskBean task8 = new TaskBean();
        task8.setName("Punkt 1");
        task8.setTime(10 * 60);
        task8.setUseBuzzer(false);
        task8.setType(TaskBean.Type.LIVING);
        list.add(task8);

        TaskBean task9 = new TaskBean();
        task9.setName("Punkt 2");
        task9.setTime(5 * 60);
        task9.setUseBuzzer(false);
        task9.setType(TaskBean.Type.LIVING);
        list.add(task9);

        if (!circuit) {
            TaskBean task10 = new TaskBean();
            task10.setName("Zborowe studium Biblii");
            task10.setTime(30 * 60);
            task10.setUseBuzzer(false);
            task10.setType(TaskBean.Type.LIVING);
            list.add(task10);
        }

        TaskBean task11 = new TaskBean();
        task11.setName("Uwagi końcowe");
        task11.setTime(3 * 60);
        task11.setUseBuzzer(false);
        task11.setType(TaskBean.Type.LIVING);
        list.add(task11);

        if (circuit) {
            ScheduleTask raw = PredefinedTask.getCircuitLecture(new ScheduleTranslator());
            TaskBean mapped = ScheduleTaskToMeetingTaskConverter.getMeetingTask(raw);
            list.add(mapped);
        }

        return list;
    }

    private static Optional<TaskBean> getTaskToEvaluate(AppConfig config, ScheduleTaskType type) {
        int timeToEvaluate = config.getTimeToEvaluate();
        if (timeToEvaluate >= 0) {
            return Optional.of(PredefinedTask.getTaskToEvaluate(new ScheduleTranslator(), timeToEvaluate, type))
                    .map(ScheduleTaskToMeetingTaskConverter::getMeetingTask);
        }
        return Optional.empty();
    }

    static List<TaskBean> getForWeekend(boolean circuit) {
        List<TaskBean> list = new ArrayList<>();

        TaskBean lecture = new TaskBean();
        lecture.setName("Wykład publiczny");
        lecture.setTime(30 * 60);
        lecture.setUseBuzzer(false);
        lecture.setType(TaskBean.Type.LECTURE);
        list.add(lecture);

        TaskBean watchtower = new TaskBean();
        watchtower.setName("Strażnica");
        if (circuit) {
            watchtower.setTime(30 * 60);
        } else {
            watchtower.setTime(60 * 60);
        }
        watchtower.setUseBuzzer(false);
        watchtower.setType(TaskBean.Type.WATCHTOWER);
        list.add(watchtower);

        if (circuit) {
            TaskBean overseerLecture = new TaskBean();
            overseerLecture.setName("Przemówienie nadzorcy obwodu");
            overseerLecture.setTime(30 * 60);
            overseerLecture.setUseBuzzer(false);
            overseerLecture.setType(TaskBean.Type.CIRCUIT);
            list.add(overseerLecture);
        }

        return list;
    }
}
