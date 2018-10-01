package jw.kingdom.hall.kingdomtimer.app;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.app.App;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.app.AppInput;
import jw.kingdom.hall.kingdomtimer.domain.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.data.config.DefaultAppConfig;
import jw.kingdom.hall.kingdomtimer.data.log.DefaultLogFile;
import jw.kingdom.hall.kingdomtimer.data.schedule.TasksFetcher;
import jw.kingdom.hall.kingdomtimer.device.local.AutoRAMCleaner;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorListManagerImpl;
import jw.kingdom.hall.kingdomtimer.device.screen.ScreenShotMaker;
import jw.kingdom.hall.kingdomtimer.device.sound.Buzzer;
import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;
import jw.kingdom.hall.kingdomtimer.domain.buzzer.BuzzerController;
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
import jw.kingdom.hall.kingdomtimer.log.Log;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Log.init(new DefaultLogFile());
        AppConfig config = DefaultAppConfig.getInstance();
        Countdown countdown = new CountdownImpl();
        Schedule schedule = new MeetingSchedule();
        RecordControl recordControl = VoiceRecorder.getInstance(config, schedule, countdown);
        TimeController time = new TimeControllerImpl(schedule, countdown);
        new BuzzerController(new Buzzer(), time);
        MonitorPreviewController speakerPreviewController = new MonitorPreviewControllerImpl(new ScreenShotMaker());
        speakerPreviewController.setPause(false);
        TasksProvider tasksProvider = new TasksFetcher(config);

        BackupManager.start(recordControl, schedule, countdown);
        MonitorListManager monitorManager = MonitorListManagerImpl.getInstance();
        try {
            new App(new AppInput() {
                @Override
                public AppConfig getConfig() {
                    return config;
                }

                @Override
                public RecordControl getRecorder() {
                    return recordControl;
                }

                @Override
                public Schedule getSchedule() {
                    return schedule;
                }

                @Override
                public Countdown getCountdown() {
                    return countdown;
                }

                @Override
                public TimeController getTimeController() {
                    return time;
                }

                @Override
                public MonitorPreviewController getSpeakerPreviewController() {
                    return speakerPreviewController;
                }

                @Override
                public TasksProvider getTasksProvider() {
                    return tasksProvider;
                }

                @Override
                public MonitorListManager getMonitorsManager() {
                    return monitorManager;
                }
            }).start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AutoRAMCleaner.run();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
