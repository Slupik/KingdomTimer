package jw.kingdom.hall.kingdomtimer.data.schedule;

import jw.kingdom.hall.kingdomtimer.domain.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.task.provider.TasksProvider;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class BackupTasksProvider implements TasksProvider {

    private final AppConfig config;

    public BackupTasksProvider(AppConfig config) {
        this.config = config;
    }

    @Override
    public void getMeetingTasks(boolean isCircuit, Callback callback) {
        getMeetingTasks(isCircuit, System.currentTimeMillis(), callback);
    }

    @Override
    public void getMeetingTasks(boolean isCircuit, long forTimeInMillis, Callback callback) {
        callback.onStart();
        List<TaskBean> list;
        if(TaskProviderUtils.isWeekend(forTimeInMillis)) {
            list = StaticTasksProvider.getForWeekend(isCircuit);
        } else {
            list = StaticTasksProvider.getForWeek(config, isCircuit);
        }
        callback.onDownload(list);
    }
}
