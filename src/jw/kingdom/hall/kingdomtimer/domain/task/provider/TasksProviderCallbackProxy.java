package jw.kingdom.hall.kingdomtimer.domain.task.provider;

import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class TasksProviderCallbackProxy implements TasksProvider.Callback {

    @Override
    public void onStart() {

    }

    @Override
    public void onProgress(short progress) {

    }

    @Override
    public void onDownload(List<MeetingTask> taskList) {

    }
}
