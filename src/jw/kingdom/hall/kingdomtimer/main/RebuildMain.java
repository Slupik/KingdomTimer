package jw.kingdom.hall.kingdomtimer.main;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.gleam.GleamSwitcher;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleProvider;
import jw.kingdom.hall.kingdomtimer.javafx.App;
import jw.kingdom.hall.kingdomtimer.javafx.AppInput;
import jw.kingdom.hall.kingdomtimer.javafx.entity.backup.BackupController;
import jw.kingdom.hall.kingdomtimer.main.backup.Backup;
import jw.kingdom.hall.kingdomtimer.main.record.Record;
import jw.kingdom.hall.kingdomtimer.main.schedule.provider.SProvider;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.usecase.monitor.MonitorListImpl;
import jw.kingdom.hall.kingdomtimer.usecase.time.buzzer.OLD_BuzzerAutoControllerImpl;
import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.OLD_CountdownControllerImpl;
import jw.kingdom.hall.kingdomtimer.usecase.time.gleam.GleamSwitcherImpl;
import jw.kingdom.hall.kingdomtimer.usecase.time.schedule.OLD_ScheduleControllerImpl;

/**
 * All rights reserved & copyright ©
 */
public class RebuildMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ScheduleController schedule = new OLD_ScheduleControllerImpl();
        OLD_CountdownControllerImpl countdown = new OLD_CountdownControllerImpl();
        GleamSwitcher gleamSwitcher = new GleamSwitcherImpl();
        new OLD_BuzzerAutoControllerImpl(new jw.kingdom.hall.kingdomtimer.main.buzzer.BuzzerPlayer(), countdown);
        Record record = new Record();
        Backup backup = new Backup();
        MonitorList mList = new MonitorListImpl();

        new App(new AppInput() {
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
                return schedule;
            }

            @Override
            public CountdownController getCountdown() {
                return countdown;
            }

            @Override
            public BuzzerPlayer getBuzzer() {
                return new jw.kingdom.hall.kingdomtimer.main.buzzer.BuzzerPlayer();
            }

            @Override
            public ScheduleProvider getScheduleProvider() {
                return new SProvider();
            }

            @Override
            public MonitorList getMonitorList() {
                return mList;
            }

            @Override
            public GleamSwitcher getGleamSwitcher() {
                return gleamSwitcher;
            }
        }).start(primaryStage);
    }
}
