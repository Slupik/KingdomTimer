package jw.kingdom.hall.kingdomtimer.main;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.CountdownControllerImpl;
import jw.kingdom.hall.kingdomtimer.usecase.time.schedule.ScheduleControllerImpl;
import jw.kingdom.hall.kingdomtimer.javafx.App;
import jw.kingdom.hall.kingdomtimer.javafx.App.Input;
import jw.kingdom.hall.kingdomtimer.javafx.entity.bussines.BackupController;
import jw.kingdom.hall.kingdomtimer.main.backup.Backup;
import jw.kingdom.hall.kingdomtimer.main.record.Record;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * All rights reserved & copyright ©
 */
public class RebuildMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ScheduleController scheduleController = new ScheduleControllerImpl();
        Record record = new Record();
        Backup backup = new Backup();

        new App(new Input() {
            @Override
            public BackupController getBackup() {
                return backup;
            }

            @Override
            public Config getConfig() {
                return AppConfig.getInstance();
            }

            @Override
            public Recorder getRecorder() {
                return record;
            }

            @Override
            public ScheduleController getSchedule() {
                return scheduleController;
            }

            @Override
            public CountdownController getCountdown() {
                return new CountdownControllerImpl();
            }
        }).start(primaryStage);
    }
}
