package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
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

    @SerializedName("fizyczne_wyswietlacze")
    @Nullable
    @Expose
    private ElementHardwareDisplay hardwareDisplays;

    public void applyParentConfig(Config parent) {
        if(speaker==null) {
            speaker = new ElementSpeaker();
        }
        if(multimedia==null) {
            multimedia = new ElementMultimedia();
        }
        if(recording==null) {
            recording = new ElementRecording();
        }
        if(countdown==null) {
            countdown = new ElementTime();
        }
        if(hardwareDisplays==null) {
            hardwareDisplays = new ElementHardwareDisplay();
        }
        speaker.applyParentConfig(parent);
        multimedia.applyParentConfig(parent);
        recording.applyParentConfig(parent);
        countdown.applyParentConfig(parent);
        hardwareDisplays.applyParentConfig(parent);
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

    public ElementHardwareDisplay getHardwareDisplays() {
        return hardwareDisplays;
    }

    public void setHardwareDisplays(ElementHardwareDisplay hardwareDisplays) {
        this.hardwareDisplays = hardwareDisplays;
    }
}
