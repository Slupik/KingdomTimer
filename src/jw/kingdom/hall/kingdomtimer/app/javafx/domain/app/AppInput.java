package jw.kingdom.hall.kingdomtimer.app.javafx.domain.app;

import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MonitorPreviewController;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.task.provider.TasksProvider;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;

/**
 * All rights reserved & copyright ©
 */
public interface AppInput {
    AppConfig getConfig();
    RecordControl getRecorder();
    Schedule getSchedule();
    Countdown getCountdown();
    TimeController getTimeController();
    MonitorPreviewController getSpeakerPreviewController();
    TasksProvider getTasksProvider();
}
