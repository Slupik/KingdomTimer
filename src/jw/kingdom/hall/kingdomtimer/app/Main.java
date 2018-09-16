package jw.kingdom.hall.kingdomtimer.app;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.app.App;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.app.AppInput;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.data.config.DefaultAppConfig;
import jw.kingdom.hall.kingdomtimer.data.log.DefaultLogFile;
import jw.kingdom.hall.kingdomtimer.device.local.AutoRAMCleaner;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingSchedule;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.log.Log;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Log.init(new DefaultLogFile());
        AppConfig config = DefaultAppConfig.getInstance();
        Schedule schedule = MeetingSchedule.getInstance();
        RecordControl recordControl = VoiceRecorder.getInstance(config, schedule);

        BackupManager.start(recordControl, schedule);
        MonitorManager.initialize();
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
            }).start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MultimediaPreviewer.getInstance().setPause(false);
        AutoRAMCleaner.run();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
