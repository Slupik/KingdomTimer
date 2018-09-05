/*
 * Created 05.09.18 02:52.
 * Last modified 05.09.18 02:38
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

/*
 * Created 05.09.18 02:37.
 * Last modified 05.09.18 02:28
 * This file is part of KingdomHallTimer which is released under "no licence".
 */


package jw.kingdom.hall.kingdomtimer.downloader.entity;

public class ScheduleTask {
    private int time = -1;
    private String name = "";
    private ScheduleTaskType type = ScheduleTaskType.UNKNOWN;

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

    public ScheduleTaskType getType() {
        return type;
    }

    public void setType(ScheduleTaskType type) {
        this.type = type;
    }

}
