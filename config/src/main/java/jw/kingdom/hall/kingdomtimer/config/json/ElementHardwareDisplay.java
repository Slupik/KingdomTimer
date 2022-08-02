package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;

/**
 * All rights reserved & copyright ©
 */
public class ElementHardwareDisplay extends ConfigElement {
    @SerializedName("over_http_get")
    @Nullable
    @Expose
    private String[] controlledByHttp;

    public String[] getControlledByHttp() throws DataParseException {
        if(isCallingParent(controlledByHttp)) {
            return parent.getIpOfHardwareTimersControlledByHttp();
        }
        return controlledByHttp;
    }

    public void setControlledByHttp(String[] controlledByHttp) {
        this.controlledByHttp = controlledByHttp;
    }
}
