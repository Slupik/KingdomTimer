package jw.kingdom.hall.kingdomtimer.data.schedule;

import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.task.provider.TasksProvider;

import java.util.Calendar;
import java.util.Date;

/**
 * All rights reserved & copyright ©
 */
public class TasksFetcher implements TasksProvider {

    private final AppConfig config;

    public TasksFetcher(AppConfig config) {
        this.config = config;
    }

    @Override
    public void getMeetingTasks(boolean isCircuit, Callback callback) {
        getMeetingTasks(isCircuit, System.currentTimeMillis(), callback);
    }

    @Override
    public void getMeetingTasks(boolean isCircuit, long forTimeInMillis, Callback callback) {
        TaskListCreator.Callback callbackForFactory = callback::onDownload;
        callback.onStart();
        if(isWeekend(forTimeInMillis)) {
            TaskListCreator.getWeekendTasks(isCircuit, callbackForFactory);
        } else {
            TaskListCreator.getWeekTasks(config, isCircuit, callbackForFactory);
        }
    }

    private static boolean isWeekend(long forTimeInMillis) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date(forTimeInMillis));
        return (cl.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                cl.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }
}
