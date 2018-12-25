package jw.kingdom.hall.kingdomtimer.domain.task.provider;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class TasksProviderCallbackProxy implements TasksProvider.Callback {

    @Override
    public void onConnectionError() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onProgress(short progress) {

    }

    @Override
    public void onDownload(List<TaskBean> taskList) {

    }
}
