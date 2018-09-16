package jw.kingdom.hall.kingdomtimer.app.javafx.domain.app;

import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;

/**
 * All rights reserved & copyright ©
 */
public interface AppInput {
    AppConfig getConfig();
    RecordControl getRecorder();
    Schedule getSchedule();
    Countdown getCountdown();
}
