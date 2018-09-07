package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.container.WindowsContainer;

/**
 * All rights reserved & copyright ©
 */
public interface TabPresentable {

    Config getConfig();
    void setConfig(Config config);

    ScheduleController getSchedule();
    void setSchedule(ScheduleController schedule);

    void setWindowsContainer(WindowsContainer windowsContainer);
    WindowsContainer getWindowsContainer();
}
