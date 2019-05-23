
/*
 * Created 05.09.18 02:34.
 * Last modified 03.09.18 21:14
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.entity;

import com.google.gson.annotations.SerializedName;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class Talk {
    @SerializedName("talkType")
    private int talkType;
    @SerializedName("minutes")
    private int minutes;
    @SerializedName("isStudentTalk")
    private boolean isStudentTalk;

    public int getTalkType() {
        return talkType;
    }

    public void setTalkType(int talkType) {
        this.talkType = talkType;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isStudentTalk() {
        return isStudentTalk;
    }

    public void setStudentTalk(boolean studentTalk) {
        isStudentTalk = studentTalk;
    }

    public String getName(){
        if(isInTrainingPart()) {
            return "Ulepszajmy swą służbę "+getTalkType();
        } else {
            return "Tryb życia punkt "+(getTalkType()-3);
        }
    }

    public boolean isInTrainingPart(){
        return getTalkType()<4;
    }
}
