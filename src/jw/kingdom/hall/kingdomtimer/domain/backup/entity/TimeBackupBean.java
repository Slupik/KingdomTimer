package jw.kingdom.hall.kingdomtimer.domain.backup.entity;

import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class TimeBackupBean {
    private OfflineMeetingBean bean;
    private List<OfflineMeetingBean> schedule;
    private boolean pause;
    private int addedTime;
    private long lastStartTime;
    private int lastTime;

    public OfflineMeetingBean getBean() {
        return bean;
    }

    public void setBean(OfflineMeetingBean bean) {
        this.bean = bean;
    }

    public List<OfflineMeetingBean> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<OfflineMeetingBean> schedule) {
        this.schedule = schedule;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(int addedTime) {
        this.addedTime = addedTime;
    }

    public long getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(long lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

}
