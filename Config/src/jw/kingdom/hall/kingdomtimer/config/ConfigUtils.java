package jw.kingdom.hall.kingdomtimer.config;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.utils.DefaultConfig;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public abstract class ConfigUtils {
    public final static String PARENT_VALUE_LINK = "{parent}";
    public final static Config DEFAULT = new DefaultConfig();
    public final static String BACKBONE = "{\"mowca\":{},\"multimedia\":{},\"nagrania\":{},\"czas\":{}}";

    private ConfigUtils(){}
}
