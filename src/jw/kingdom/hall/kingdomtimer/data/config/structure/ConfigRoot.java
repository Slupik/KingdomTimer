package jw.kingdom.hall.kingdomtimer.data.config.structure;

import com.google.gson.annotations.SerializedName;

/**
 * All rights reserved & copyright ©
 */
public class ConfigRoot {
    @SerializedName("wersjaConfigu")
    private String version;

    @SerializedName("ekranMowcy")
    private ConfigSpeaker speaker;

    public ConfigSpeaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(ConfigSpeaker speaker) {
        this.speaker = speaker;
    }
}
