package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;

/**
 * All rights reserved & copyright ©
 */
public interface TabPresentable {

    Config getConfig();
    void setConfig(Config config);

    ScheduleController getSchedule();
    void setSchedule(ScheduleController schedule);
}
