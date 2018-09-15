package jw.kingdom.hall.kingdomtimer.javafx;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer;
import jw.kingdom.hall.kingdomtimer.entity.time.gleam.GleamSwitcher;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleProvider;
import jw.kingdom.hall.kingdomtimer.javafx.entity.backup.BackupController;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.usecase.time.controller.TimeController;
import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.usecase.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.usecase.usecase.edit.schedule.IBEditSchedule;

/**
 * All rights reserved & copyright ©
 */
public interface AppInput {
    BackupController getBackup();
    Config getConfig();
    Recorder getRecorder();
    TimeController getTimeController();
    ScheduleController getSchedule();
    IBEditSchedule getScheduleEditor();
    CountdownController getCountdown();
    BuzzerPlayer getBuzzer();
    ScheduleProvider getScheduleProvider();
    MonitorList getMonitorList();
    GleamSwitcher getGleamSwitcher();

}
