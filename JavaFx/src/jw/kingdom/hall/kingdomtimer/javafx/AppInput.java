package jw.kingdom.hall.kingdomtimer.javafx;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.monitor.MonitorList;
import jw.kingdom.hall.kingdomtimer.entity.time.buzzer.BuzzerPlayer;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleProvider;
import jw.kingdom.hall.kingdomtimer.javafx.entity.backup.BackupController;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * All rights reserved & copyright ©
 */
public interface AppInput {
    BackupController getBackup();
    Config getConfig();
    Recorder getRecorder();
    ScheduleController getSchedule();
    CountdownController getCountdown();
    BuzzerPlayer getBuzzer();
    ScheduleProvider getScheduleProvider();
    MonitorList getMonitorList();
}
