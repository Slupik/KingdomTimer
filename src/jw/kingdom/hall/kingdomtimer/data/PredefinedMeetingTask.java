package jw.kingdom.hall.kingdomtimer.data;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

/**
 * All rights reserved & copyright ©
 */
class PredefinedMeetingTask {

    static MeetingTask getPreview(){
        MeetingTask task = new MeetingTask();
        task.setName("Uwagi wstępne");
        task.setUseBuzzer(false);
        task.setTimeInSeconds(3*60);
        task.setType(MeetingTask.Type.TREASURES);
        return task;
    }

    static MeetingTask getBibleAnalyst(){
        MeetingTask task = new MeetingTask();
        task.setName("Analiza Biblii");
        task.setUseBuzzer(false);
        task.setTimeInSeconds(10*60);
        task.setType(MeetingTask.Type.TREASURES);
        return task;
    }

    static MeetingTask getBibleTreasure(){
        MeetingTask task = new MeetingTask();
        task.setName("Wyszukujemy duchowe skarby");
        task.setUseBuzzer(false);
        task.setTimeInSeconds(8*60);
        task.setType(MeetingTask.Type.TREASURES);
        return task;
    }

    static MeetingTask getBibleReading(){
        MeetingTask task = new MeetingTask();
        task.setName("Czytanie Biblii");
        task.setUseBuzzer(true);
        task.setTimeInSeconds(4*60);
        task.setType(MeetingTask.Type.TREASURES);
        return task;
    }

    /*
    SECOND PART OF MEETING BELOW
     */

    static MeetingTask getBookAnalyst(){
        MeetingTask task = new MeetingTask();
        task.setName("Zborowe studium Biblii");
        task.setUseBuzzer(false);
        task.setTimeInSeconds(30*60);
        task.setType(MeetingTask.Type.LIVING);
        return task;
    }

    static MeetingTask getRepeat(){
        MeetingTask task = new MeetingTask();
        task.setName("Powtórka i zapowiedź następnego zebrania");
        task.setUseBuzzer(false);
        task.setTimeInSeconds(3*60);
        task.setType(MeetingTask.Type.LIVING);
        return task;
    }
}
