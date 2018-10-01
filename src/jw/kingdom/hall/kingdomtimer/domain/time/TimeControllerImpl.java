package jw.kingdom.hall.kingdomtimer.domain.time;

import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.CountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.schedule.NotEnoughTasksException;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.schedule.ScheduleListener;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class TimeControllerImpl implements TimeController {

    private final Schedule schedule;
    private final Countdown countdown;
    private final List<TimeListener> listeners = new ArrayList<>();

    public TimeControllerImpl(Schedule schedule, Countdown countdown) {
        this.schedule = schedule;
        this.countdown = countdown;
        initListeners();
    }

    @Override
    public void addTask(TaskBean... tasks) {
        schedule.addTask(tasks);
    }

    @Override
    public void removeTask(TaskBean... tasks) {
        for(TaskBean toRemove:tasks) {
            schedule.removeTask(toRemove);
        }
    }

    @Override
    public void removeTask(int index) {
        schedule.removeTask(index);
    }

    @Override
    public void clear() {
        schedule.clear();
    }

    @Override
    public void setList(List<TaskBean> list) {
        schedule.setList(list);
    }

    @Override
    public List<TaskBean> getList() {
        return schedule.getList();
    }

    @Override
    public void startNext() {
        try {
            TaskBean task = schedule.bringOutFirstTask();
            countdown.start(task);
        } catch (NotEnoughTasksException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(TaskBean task) {
        countdown.start(task);
    }

    @Override
    public void stop() {
        countdown.stop();
    }

    @Override
    public void pause() {
        countdown.pause();
    }

    @Override
    public void resume() {
        countdown.resume();
    }

    @Override
    public TaskBean getActualTask() {
        return countdown.getTask();
    }

    @Override
    public void addTime(int time) {
        countdown.addTime(time);
    }

    @Override
    public int getAddedTime() {
        return countdown.getAddedTime();
    }

    @Override
    public int getTime() {
        return countdown.getTime();
    }

    @Override
    public void enforceTime(int time) {
        countdown.enforceTime(time);
    }

    @Override
    public void addDisplay(TimeDisplay display) {
        countdown.addDisplay(display);
    }

    @Override
    public void removeDisplay(TimeDisplay display) {
        countdown.removeDisplay(display);
    }

    @Override
    public void addListener(TimeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(TimeListener listener) {
        listeners.remove(listener);
    }

    private Schedule getSchedule() {
        return schedule;
    }

    private Countdown getCountdown() {
        return countdown;
    }

    private void initListeners() {
        initListenerForCountdown();
        initListenerForSchedule();
    }

    private boolean hasMeetingStarted = false;

    private void initListenerForSchedule() {
        getSchedule().addListener(new ScheduleListener() {

            @Override
            public void onRemove(TaskBean task) {
                notifyOnListChange(getSchedule().getList());
            }

            @Override
            public void onRemove(int index, TaskBean removed) {
                notifyOnListChange(getSchedule().getList());
            }

            @Override
            public void onInsert(TaskBean task) {
                notifyOnListChange(getSchedule().getList());
            }

            @Override
            public void onBulkInsert(TaskBean... task) {
                notifyOnListChange(getSchedule().getList());
            }

            @Override
            public void onClear() {
                hasMeetingStarted = false;
                notifyOnListChange(getSchedule().getList());
            }

            @Override
            public void onReset(List<TaskBean> newList) {
                hasMeetingStarted = false;
                notifyOnListChange(newList);
            }

            @Override
            public void onMove(int elementIndex, int destIndex) {
                notifyOnListChange(getSchedule().getList());
            }

            private void notifyOnListChange(List<TaskBean> list) {
                for(TimeListener listener:listeners) {
                    listener.onScheduleChange(list);
                }
            }
        });
    }

    private void initListenerForCountdown() {
        getCountdown().addListener(new CountdownListener() {

            @Override
            public void onTimeOut() {}

            @Override
            public void onStart(TaskBean task) {
                if(!hasMeetingStarted) {
                    hasMeetingStarted = true;
                    for(TimeListener listener:listeners) {
                        listener.onMeetingStart();
                    }
                }
                for(TimeListener listener:listeners) {
                    listener.onStart(task);
                }
            }

            @Override
            public void onPause() {
                for(TimeListener listener:listeners) {
                    listener.onPause();
                }
            }

            @Override
            public void onResume() {
                for(TimeListener listener:listeners) {
                    listener.onResume();
                }
            }

            @Override
            public void onStop() {
                if(getSchedule().getList().size()==0) {
                    for(TimeListener listener:listeners) {
                        listener.onMeetingEnd();
                    }
                }
                for(TimeListener listener:listeners) {
                    listener.onStop();
                }
            }

            @Override
            public void onTimeManipulate(int totalAdded, int added) {
                for(TimeListener listener:listeners) {
                    listener.onTimeAdded(totalAdded, added);
                }
            }

            @Override
            public void onEnforceTime(int time) {
                for(TimeListener listener:listeners) {
                    listener.onTimeEnforce(time);
                }
            }
        });
    }
}
