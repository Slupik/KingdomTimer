
/*
 * Created 05.09.18 02:34.
 * Last modified 03.09.18 21:14
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.entity;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class Meeting {
    @SerializedName("date")
    private String dateText;
    @SerializedName("talks")
    private List<Talk> talks;
    @SerializedName("songNumbers")
    private List<Integer> songNumbers;

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    public List<Integer> getSongNumbers() {
        return songNumbers;
    }

    public void setSongNumbers(List<Integer> songNumbers) {
        this.songNumbers = songNumbers;
    }

    public int getWeekOfYear(){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = inputFormat.parse(dateText);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.WEEK_OF_YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean isWeekMeeting() {
        return talks.size()!=0;//Only at weekend meetings is 0 talks
    }
}
