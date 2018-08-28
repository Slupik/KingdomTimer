package jw.kingdom.hall.kingdomtimer.config.common;

import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * All rights reserved & copyright ©
 */
public class DataParseException extends Exception {
    public DataParseException(String value, ConfigFieldType type){
        super("Cannot parse \""+value+"\" to type: "+type);
    }
}
