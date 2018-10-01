package jw.kingdom.hall.kingdomtimer.data.config;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;

/**
 * All rights reserved & copyright ©
 */
public interface AppConfig extends Config {
    void setSpeakerScreen(Monitor monitor);
    void setMultimediaScreen(Monitor monitor);
}
