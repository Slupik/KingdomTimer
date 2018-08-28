package jw.kingdom.hall.kingdomtimer.config.model;

/**
 * All rights reserved & copyright ©
 */
public interface ConfigWriteable extends Config {
    void loadLocalData(String data);
    void loadParent(Config parent);
    void fillWith(Config source);
}
