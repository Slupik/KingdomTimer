package jw.kingdom.hall.kingdomtimer.entity.monitor;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public interface MonitorList {

    List<Monitor> getMonitors();

    void addListener(Listener listener);
    void removeListener(Listener listener);

    Monitor findById(String speakerScreen);

    interface Listener {
        void onChange();
    }
}
