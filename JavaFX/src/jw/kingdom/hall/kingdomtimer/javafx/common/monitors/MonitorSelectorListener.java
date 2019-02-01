package jw.kingdom.hall.kingdomtimer.javafx.common.monitors;

import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;

/**
 * All rights reserved & copyright ©
 */
@FunctionalInterface
public interface MonitorSelectorListener {

    void onSelectNewMonitor(Monitor monitor);
}
