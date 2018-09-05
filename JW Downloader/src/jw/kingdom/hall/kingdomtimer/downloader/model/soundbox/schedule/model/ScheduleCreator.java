/*
 * Created 05.09.18 02:57.
 * Last modified 05.09.18 02:57
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.model;

import jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTask;
import jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.entity.Meeting;
import jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.entity.Talk;

import java.util.ArrayList;
import java.util.List;

import static jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType.LIVING;
import static jw.kingdom.hall.kingdomtimer.downloader.entity.ScheduleTaskType.MINISTRY;

class ScheduleCreator {
    static List<ScheduleTask> getAllWeekTasks(boolean circuit, Meeting meeting) {
        List<ScheduleTask> tasks = new ArrayList<>();
        tasks.add(PredefinedTask.getPreview());
        tasks.add(PredefinedTask.getBibleAnalyst());
        tasks.add(PredefinedTask.getBibleTreasure());
        tasks.add(PredefinedTask.getBibleReading());

        if(meeting!=null) {
            int i=0;
            for(Talk talk:meeting.getTalks()) {
                ScheduleTask task = TalkToTaskConverter.getConverted(talk);
                if(i<3) {
                    task.setType(MINISTRY);
                } else {
                    task.setType(LIVING);
                }
                tasks.add(task);
                i++;
            }
        }
        if(!circuit) {
            tasks.add(PredefinedTask.getBookAnalyst());
        }
        tasks.add(PredefinedTask.getRepeat());
        if(circuit) {
            tasks.add(PredefinedTask.getCircuitLecture());
        }
        return tasks;
    }
}
