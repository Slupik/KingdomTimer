package jw.kingdom.hall.kingdomtimer.domain.backup.entity;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class OfflineMeetingBean {
    public final String ID;
    private boolean useBuzzer;
    private String name;
    private boolean countdownDown;
    private int time;
    private TaskBean.Type type;

    public OfflineMeetingBean() {
        this(new TaskBean());
    }

    public OfflineMeetingBean(TaskBean task) {
        ID = task.ID;
        useBuzzer = task.isUseBuzzer();
        name = task.getName();
        countdownDown = task.isCountdownDown();
        time = task.getTime();
        type = task.getType();
    }

    public TaskBean convertToMeetingTask(){
        TaskBean task = new TaskBean(ID);
        task.setUseBuzzer(useBuzzer);
        task.setName(name);
        task.setCountdownDown(countdownDown);
        task.setTime(time);
        task.setType(type);
        return task;
    }

    public boolean isUseBuzzer() {
        return useBuzzer;
    }

    public void setUseBuzzer(boolean useBuzzer) {
        this.useBuzzer = useBuzzer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCountdownDown() {
        return countdownDown;
    }

    public void setCountdownDown(boolean countdownDown) {
        this.countdownDown = countdownDown;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public TaskBean.Type getType() {
        return type;
    }

    public void setType(TaskBean.Type type) {
        this.type = type;
    }

}
