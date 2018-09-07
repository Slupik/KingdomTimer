package jw.kingdom.hall.kingdomtimer.javafx.entity.view;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.javafx.loader.ViewManager;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;

/**
 * All rights reserved & copyright ©
 */
public interface ControlledScreen {

    void setWindow(AppWindow window);

    void setViewManager(ViewManager viewManager);

    Config getConfig();
    void setConfig(Config config);

    ScheduleController getSchedule();
    void setSchedule(ScheduleController schedule);
}
