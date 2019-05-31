package jw.kingdom.hall.kingdomtimer;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.data.file.save.backup.BackupFileControllerImpl;
import jw.kingdom.hall.kingdomtimer.data.file.save.log.DefaultLogFileProvider;
import jw.kingdom.hall.kingdomtimer.device.external.timer.http.ServerForHttpDisplays;
import jw.kingdom.hall.kingdomtimer.device.external.timer.http.ServerForHttpDisplaysImpl;
import jw.kingdom.hall.kingdomtimer.device.local.AutoRAMCleaner;
import jw.kingdom.hall.kingdomtimer.device.sound.Buzzer;
import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;
import jw.kingdom.hall.kingdomtimer.domain.buzzer.BuzzerController;
import jw.kingdom.hall.kingdomtimer.domain.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorListManager;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MonitorPreviewController;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.task.provider.TasksProvider;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.log.Log;
import jw.kingdom.hall.kingdomtimer.webui.domain.app.App;
import jw.kingdom.hall.kingdomtimer.webui.domain.app.AppInput;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Log.init(new DefaultLogFileProvider());

        MonitorPreviewController speakerPreviewController = ComponentsCreator.getMonitorPreviewController();
        MonitorListManager monitorManager = ComponentsCreator.getMonitorListManager();
        AppConfig config = ComponentsCreator.getAppConfig();
        Countdown countdown = ComponentsCreator.getCountdown();
        Schedule schedule = ComponentsCreator.getSchedule();

        RecordControl recordControl = ComponentsCreator.getVoiceRecorder(config, schedule, countdown);
        TimeController time = ComponentsCreator.getTimeController(schedule, countdown);
        TasksProvider tasksProvider = ComponentsCreator.getTaskProvider(config);

        configureHttpDisplaysController(time, config);
        initBuzzerController(time);
        runBackup(recordControl, schedule, countdown);

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

    private void runBackup(RecordControl recordControl, Schedule schedule, Countdown countdown) {
        BackupManager.start(recordControl, schedule, countdown, new BackupFileControllerImpl());
    }

    private void initBuzzerController(TimeController time) {
        new BuzzerController(new Buzzer(), time).init();
    }

    private void configureHttpDisplaysController(TimeController time, AppConfig config) {
        ServerForHttpDisplays controller = new ServerForHttpDisplaysImpl(time);
        for(String ip:config.getIpOfHardwareTimersControlledByHttp()) {
            controller.add(ip);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
