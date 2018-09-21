package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.Nullable;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;
import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class ElementMultimedia extends ConfigElement {
    @SerializedName("ekran")
    @Nullable
    @Expose
    private String screen;

    @SerializedName("min_refresh_rate")
    @Nullable
    @Expose
    private String minRefreshRate;

    @SerializedName("warning_refresh_rate")
    @Nullable
    @Expose
    private String warningRefreshRate;

    @SerializedName("default_refresh_rate")
    @Nullable
    @Expose
    private String defaultRefreshRate;

    @SerializedName("actual_refresh_rate")
    @Nullable
    @Expose
    private String actualRefreshRate;

    public String getScreen() {
        if(isCallingParent(screen)) {
            return parent.getMultimediaScreen();
        }
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public int getMinRefreshRate() throws DataParseException {
        if(isCallingParent(minRefreshRate, ConfigFieldType.INTEGER)) {
            return parent.getMinRefreshRate();
        }
        return toInteger(minRefreshRate);
    }

    public void setMinRefreshRate(int minRefreshRate) {
        this.minRefreshRate = Integer.toString(minRefreshRate);
    }

    public int getWarningRefreshRate() throws DataParseException {
        if(isCallingParent(warningRefreshRate, ConfigFieldType.INTEGER)) {
            return parent.getWarningRefreshRate();
        }
        return toInteger(warningRefreshRate);
    }

    public void setWarningRefreshRate(int warningRefreshRate) {
        this.warningRefreshRate = Integer.toString(warningRefreshRate);
    }

    public int getDefaultRefreshRate() throws DataParseException {
        if(isCallingParent(defaultRefreshRate, ConfigFieldType.INTEGER)) {
            return parent.getDefaultRefreshRate();
        }
        return toInteger(defaultRefreshRate);
    }

    public void setDefaultRefreshRate(int defaultRefreshRate) {
        this.defaultRefreshRate = Integer.toString(defaultRefreshRate);
    }

    public int getActualRefreshRate() throws DataParseException {
        if(isCallingParent(actualRefreshRate, ConfigFieldType.INTEGER)) {
            return parent.getActualRefreshRate();
        }
        return toInteger(actualRefreshRate);
    }

    public void setActualRefreshRate(int actualRefreshRate) {
        this.actualRefreshRate = Integer.toString(actualRefreshRate);
    }
}
