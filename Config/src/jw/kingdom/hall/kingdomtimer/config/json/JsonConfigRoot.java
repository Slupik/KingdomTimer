package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.config.Config;

/**
 * All rights reserved & copyright ©
 */
class JsonConfigRoot extends ConfigElement {
    @SerializedName("mowca")
    @Nullable
    private ElementSpeaker speaker;

    @SerializedName("multimedia")
    @Nullable
    private ElementMultimedia multimedia;

    @SerializedName("nagrania")
    @Nullable
    private ElementRecording recording;

    @SerializedName("czas")
    @Nullable
    private ElementTime countdown;

    public void applyParentConfig(Config parent) {
        if(speaker!=null) {
            speaker.applyParentConfig(parent);
        }
        if(multimedia!=null) {
            multimedia.applyParentConfig(parent);
        }
        if(recording!=null) {
            recording.applyParentConfig(parent);
        }
        if(countdown!=null) {
            countdown.applyParentConfig(parent);
        }
    }

    public ElementSpeaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(ElementSpeaker speaker) {
        this.speaker = speaker;
    }

    public ElementMultimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(ElementMultimedia multimedia) {
        this.multimedia = multimedia;
    }

    public ElementRecording getRecording() {
        return recording;
    }

    public void setRecording(ElementRecording recording) {
        this.recording = recording;
    }

    public ElementTime getCountdown() {
        return countdown;
    }

    public void setCountdown(ElementTime countdown) {
        this.countdown = countdown;
    }
}
