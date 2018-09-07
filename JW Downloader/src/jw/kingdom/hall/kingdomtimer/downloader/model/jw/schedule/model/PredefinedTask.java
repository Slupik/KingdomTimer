/*
 * Created 05.09.18 03:54.
 * Last modified 05.09.18 03:54
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTranslator;

import static jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType.CIRCUIT;
import static jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType.MINISTRY;

public class PredefinedTask {
    static ScheduleTask getCircuitLecture(ScheduleTranslator translator) {
        ScheduleTask task = new ScheduleTask();
        task.setName(translator.circuitLecture());
        task.setTime(30*60);
        task.setActiveBuzzer(false);
        task.setType(CIRCUIT);
        return task;
    }

    static ScheduleTask getTaskToEvaluate(ScheduleTranslator translator, int seconds) {
        ScheduleTask task = new ScheduleTask();
        task.setName(translator.evaluate());
        task.setTime(seconds);
        task.setActiveBuzzer(false);
        task.setType(MINISTRY);
        return task;
    }
}
