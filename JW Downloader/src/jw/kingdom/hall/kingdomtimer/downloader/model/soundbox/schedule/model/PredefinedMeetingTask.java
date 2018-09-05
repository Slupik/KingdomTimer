/*
 * Created 05.09.18 03:03.
 * Last modified 03.09.18 21:14
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;

import static jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType.*;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class PredefinedMeetingTask {

    static ScheduleTask getPreview(){
        ScheduleTask task = new ScheduleTask();
        task.setName("Uwagi wstępne");
        task.setTime(3*60);
        task.setType(TREASURES);
        return task;
    }

    static ScheduleTask getBibleAnalyst(){
        ScheduleTask task = new ScheduleTask();
        task.setName("Analiza Biblii");
        task.setTime(10*60);
        task.setType(TREASURES);
        return task;
    }

    static ScheduleTask getBibleTreasure(){
        ScheduleTask task = new ScheduleTask();
        task.setName("Wyszukujemy duchowe skarby");
        task.setTime(8*60);
        task.setType(TREASURES);
        return task;
    }

    static ScheduleTask getBibleReading(){
        ScheduleTask task = new ScheduleTask();
        task.setName("Czytanie Biblii");
        task.setTime(4*60);
        task.setType(TREASURES);
        return task;
    }

    /*
    SECOND PART OF MEETING BELOW
     */

    static ScheduleTask getBookAnalyst(){
        ScheduleTask task = new ScheduleTask();
        task.setName("Zborowe studium Biblii");
        task.setTime(30*60);
        task.setType(LIVING);
        return task;
    }

    static ScheduleTask getRepeat(){
        ScheduleTask task = new ScheduleTask();
        task.setName("Powtórka i zapowiedź następnego zebrania");
        task.setTime(3*60);
        task.setType(LIVING);
        return task;
    }

    static ScheduleTask getOverseerLecture() {
        ScheduleTask task = new ScheduleTask();
        task.setName("Przemówienie podróżującego");
        task.setTime(30*60);
        task.setType(CIRCUIT);
        return task;
    }
}
