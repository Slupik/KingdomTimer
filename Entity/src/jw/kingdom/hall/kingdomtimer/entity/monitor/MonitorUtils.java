package jw.kingdom.hall.kingdomtimer.entity.monitor;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class MonitorUtils {
    private MonitorUtils(){}

    public static Monitor getPrimary(List<Monitor> list) throws MonitorNotFoundException {
        for(Monitor monitor:list) {
            if(monitor.isPrimary()) {
                return monitor;
            }
        }
        throw new MonitorNotFoundException();
    }
}
