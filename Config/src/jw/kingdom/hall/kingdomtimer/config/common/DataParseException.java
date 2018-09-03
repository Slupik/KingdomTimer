package jw.kingdom.hall.kingdomtimer.config.common;

import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class DataParseException extends Exception {
    public DataParseException(String value, ConfigFieldType type){
        super("Cannot parse \""+value+"\" to type: "+type);
    }
}
