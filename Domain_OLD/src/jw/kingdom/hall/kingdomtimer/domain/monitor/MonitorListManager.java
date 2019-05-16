package jw.kingdom.hall.kingdomtimer.domain.monitor;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface MonitorListManager {
    void addListener(MonitorEventHandler handler);
    void removeListener(MonitorEventHandler handler);
    List<Monitor> getAll();
}
