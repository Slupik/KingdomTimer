package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;
import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * All rights reserved & copyright ©
 */
class ElementRecording extends ConfigElement {
    @SerializedName("sciezka")
    @Nullable
    @Expose
    private String path;

    @SerializedName("autopilot")
    @Nullable
    @Expose
    private String autopilot;

    public String getPath() {
        if(isCallingParent(path)) {
            return parent.getRecordDestPath();
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getAutopilot() throws DataParseException {
        if(isCallingParent(autopilot, ConfigFieldType.BOOLEAN)) {
            return parent.isEnabledAutopilot();
        }
        return toBoolean(autopilot);
    }

    public void setAutopilot(boolean autopilot) {
        this.autopilot = Boolean.toString(autopilot);
    }
}
