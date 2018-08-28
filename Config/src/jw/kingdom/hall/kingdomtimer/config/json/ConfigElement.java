package jw.kingdom.hall.kingdomtimer.config.json;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.ConfigUtils;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;
import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * All rights reserved & copyright ©
 */
public abstract class ConfigElement {
    protected Config parent;

    public void applyParentConfig(Config parent) {
        this.parent = parent;
    }

    protected boolean isCallingParent(String value) {
        return isCallingParent(value, ConfigFieldType.STRING);
    }
    protected boolean isCallingParent(String value, ConfigFieldType type) {
        if(null == value || value.equalsIgnoreCase(ConfigUtils.PARENT_VALUE_LINK)){
            return true;
        }
        return !isCorrect(value, type);
    }

    private boolean isCorrect(String value, ConfigFieldType type) {
        try {
            switch (type) {
                case STRING: {
                    return true;
                }
                case BOOLEAN: {
                    toBoolean(value);
                    return true;
                }
                case INTEGER: {
                    toInteger(value);
                    return true;
                }
                default: {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean toBoolean(String value) throws DataParseException {
        if(value.equalsIgnoreCase("tak") || value.equalsIgnoreCase("yes")
                || value.equalsIgnoreCase("true")|| value.equalsIgnoreCase("prawda")) {
            return true;
        }
        if(value.equalsIgnoreCase("nie") || value.equalsIgnoreCase("no")
                || value.equalsIgnoreCase("nope") || value.equalsIgnoreCase("false")
                || value.equalsIgnoreCase("FAŁSZ")) {
            return false;
        }
        throw new DataParseException(value, ConfigFieldType.BOOLEAN);
    }

    protected int toInteger(String value) throws DataParseException {
        try {
            return Integer.parseInt(value);
        } catch (Exception ignore) {
            throw new DataParseException(value, ConfigFieldType.INTEGER);
        }
    }
}
