package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;
import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * All rights reserved & copyright ©
 */
class ElementSpeaker extends ConfigElement {
    @SerializedName("ekran")
    @Nullable
    @Expose
    private String screen;

    @SerializedName("miganie")
    @Nullable
    @Expose
    private String gleaming;

    @SerializedName("pokaz_multimedia")
    @Nullable
    @Expose
    private String showMulti;

    @SerializedName("pokaz_ekran")
    @Nullable
    @Expose
    private String isVisible;

    public String getScreen() {
        if(isCallingParent(screen)) {
            return parent.getSpeakerScreen();
        }
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public boolean isGleaming() throws DataParseException {
        if(isCallingParent(gleaming, ConfigFieldType.BOOLEAN)) {
            return parent.isEnabledGleaming();
        }
        return toBoolean(gleaming);
    }

    public void setGleaming(boolean gleaming) {
        this.gleaming = Boolean.toString(gleaming);
    }

    public boolean isShowMulti() throws DataParseException {
        if(isCallingParent(showMulti, ConfigFieldType.BOOLEAN)) {
            return parent.isEnabledShowMultimedia();
        }
        return toBoolean(showMulti);
    }

    public void setShowMulti(boolean showMulti) {
        this.showMulti = Boolean.toString(showMulti);
    }

    public boolean isVisible() throws DataParseException {
        if(isCallingParent(isVisible, ConfigFieldType.BOOLEAN)) {
            return parent.isVisibleSpeakerScreen();
        }
        return toBoolean(isVisible);
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = Boolean.toString(isVisible);
    }
}
