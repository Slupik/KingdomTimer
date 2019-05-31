package jw.kingdom.hall.kingdomtimer;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.file.config.DefaultAppConfig;
import jw.kingdom.hall.kingdomtimer.data.file.save.record.DefaultFileRecordProvider;
import jw.kingdom.hall.kingdomtimer.data.schedule.TasksFetcher;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorListManagerImpl;
import jw.kingdom.hall.kingdomtimer.device.screen.ScreenShotMaker;
import jw.kingdom.hall.kingdomtimer.domain.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.CountdownImpl;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorListManager;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MonitorPreviewController;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MonitorPreviewControllerImpl;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.task.provider.TasksProvider;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeControllerImpl;

/**
 * All rights reserved & copyright ©
 */
class ComponentsCreator {
    static AppConfig getAppConfig() {
        return DefaultAppConfig.getInstance();
    }

    static Countdown getCountdown() {
        return new CountdownImpl();
    }

    static Schedule getSchedule() {
        return new MeetingSchedule();
    }

    static RecordControl getVoiceRecorder(Config config, Schedule schedule, Countdown countdown) {
        return VoiceRecorder.getInstance(new DefaultFileRecordProvider(config, schedule, countdown));
    }

    static TimeController getTimeController(Schedule schedule, Countdown countdown) {
        return new TimeControllerImpl(schedule, countdown);
    }

    static MonitorPreviewController getMonitorPreviewController() {
        MonitorPreviewController speakerPreviewController = new MonitorPreviewControllerImpl(new ScreenShotMaker());
        speakerPreviewController.setPause(false);
        return speakerPreviewController;
    }

    static TasksProvider getTaskProvider(AppConfig config) {
        return new TasksFetcher(config);
    }

    static MonitorListManager getMonitorListManager() {
        return MonitorListManagerImpl.getInstance();
    }
}
