package jw.kingdom.hall.kingdomtimer.entity.time.schedule;

import jw.kingdom.hall.kingdomtimer.entity.task.ObservableTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface ScheduleProvider {
    void getForToday(Data input, Callback callback);

    interface Callback {
        void onDataReceive(List<ObservableTask> data);
    }

    interface Data {
        boolean isCircuitVisit();
    }
}
