/*
 * Created 05.09.18 00:39.
 * Last modified 05.09.18 00:39
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.jw.schedule.entity;

public class JwTask {
    private int time = -1;
    private String name = "";
    private JwType type = JwType.UNKNOWN;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JwType getType() {
        return type;
    }

    public void setType(JwType type) {
        this.type = type;
    }

}
