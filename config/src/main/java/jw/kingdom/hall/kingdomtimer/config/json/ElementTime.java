package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import jw.kingdom.hall.kingdomtimer.config.common.DataParseException;
import jw.kingdom.hall.kingdomtimer.config.utils.ConfigFieldType;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class ElementTime extends ConfigElement {
    @SerializedName("odliczanie_dol")
    @Nullable
    @Expose
    private String countdownDown;
    @SerializedName("czas_na_ocene")
    @Nullable
    @Expose
    private String timeToEvaluate;
    @SerializedName("czas_zebrania")
    @Nullable
    @Expose
    private String meetingTime;

    public boolean getCountdownDown() throws DataParseException {
        if(isCallingParent(countdownDown, ConfigFieldType.BOOLEAN)) {
            return parent.isDirectDown();
        }
        return toBoolean(countdownDown);
    }

    public void setCountdownDown(boolean countdownDown) {
        this.countdownDown = Boolean.toString(countdownDown);
    }

    public int getTimeToEvaluate() throws DataParseException {
        if(isCallingParent(timeToEvaluate, ConfigFieldType.INTEGER)) {
            return parent.getTimeToEvaluate();
        }
        return toInteger(timeToEvaluate);
    }

    public void setTimeToEvaluate(int seconds) {
        this.timeToEvaluate = Integer.toString(seconds);
    }

    public int getMeetingTime() throws DataParseException {
        if(isCallingParent(meetingTime, ConfigFieldType.INTEGER)) {
            return parent.getMeetingTime();
        }
        return toInteger(meetingTime);
    }

    public void setMeetingTime(int seconds) {
        this.meetingTime = Integer.toString(seconds);
    }
}
