package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.config.model.Config;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class JsonConfigRoot extends ConfigElement {
    @SerializedName("mowca")
    @Nullable
    @Expose
    private ElementSpeaker speaker;

    @SerializedName("multimedia")
    @Nullable
    @Expose
    private ElementMultimedia multimedia;

    @SerializedName("nagrania")
    @Nullable
    @Expose
    private ElementRecording recording;

    @SerializedName("czas")
    @Nullable
    @Expose
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
