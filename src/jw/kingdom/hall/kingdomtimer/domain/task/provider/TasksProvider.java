package jw.kingdom.hall.kingdomtimer.domain.task.provider;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface TasksProvider {
    void getMeetingTasks(boolean isCircuit, Callback callback);
    void getMeetingTasks(boolean isCircuit, long forTimeInMillis, Callback callback);

    interface Callback {
        void onStart();
        void onProgress(short progress);
        void onDownload(List<TaskBean> taskList);
    }
}
