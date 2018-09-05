/*
 * Created 05.09.18 03:54.
 * Last modified 05.09.18 03:54
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.jw.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTranslator;

import static jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType.CIRCUIT;

public class PredefinedTask {
    static ScheduleTask getCircuitLecture(ScheduleTranslator translator) {
        ScheduleTask task = new ScheduleTask();
        task.setName(translator.circuitLecture());
        task.setTime(30*60);
        task.setType(CIRCUIT);
        return task;
    }
}
