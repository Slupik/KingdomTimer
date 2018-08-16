package jw.kingdom.hall.kingdomtimer.data.online.model;

import com.google.gson.annotations.SerializedName;

/**
 * All rights reserved & copyright ©
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
