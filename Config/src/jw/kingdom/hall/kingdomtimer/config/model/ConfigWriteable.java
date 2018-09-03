package jw.kingdom.hall.kingdomtimer.config.model;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface ConfigWriteable extends Config {
    void loadLocalData(String data);
    void loadParent(Config parent);
    void fillWith(Config source);
}
