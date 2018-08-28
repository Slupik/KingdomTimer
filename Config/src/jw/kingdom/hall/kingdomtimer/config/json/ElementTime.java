package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;
import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * All rights reserved & copyright ©
 */
class ElementTime extends ConfigElement {
    @SerializedName("odliczanie_dol")
    @Nullable
    private String countdownDown;

    public boolean getCountdownDown() throws DataParseException {
        if(isCallingParent(countdownDown, ConfigFieldType.BOOLEAN)) {
            return parent.isDirectDown();
        }
        return toBoolean(countdownDown);
    }

    public void setCountdownDown(boolean countdownDown) {
        this.countdownDown = Boolean.toString(countdownDown);
    }
}
