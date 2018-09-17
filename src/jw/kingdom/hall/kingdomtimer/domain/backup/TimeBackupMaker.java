package jw.kingdom.hall.kingdomtimer.domain.backup;

import com.google.gson.GsonBuilder;
import javafx.beans.value.ChangeListener;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.OfflineMeetingBean;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.TimeBackupBean;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.TimerCountdownListener;
import jw.kingdom.hall.kingdomtimer.domain.model.MeetingTask;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.DefaultVoiceRecorderRecordControlListener;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingScheduleListener;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class TimeBackupMaker {
    private final Schedule schedule;
    private final Countdown countdown;
    private TimeBackupBean bean = new TimeBackupBean();
    private ChangeListener<Boolean> buzzerListener = (observable, oldValue, newValue) -> updateBuzzer(newValue);
    private ChangeListener<Boolean> countdownDirectListener = (observable, oldValue, newValue) -> updateDirect(newValue);
    private MeetingTask lastTask = null;

    TimeBackupMaker(RecordControl recordControl, Schedule schedule, Countdown countdown){
        this.schedule = schedule;
        this.countdown = countdown;
        getSchedule().addListener(new MeetingScheduleListener() {

            @Override
            public void onRemove(MeetingTask task) {
                updateSchedule();
            }

            @Override
            public void onInsert(MeetingTask task) {
                updateSchedule();
            }

            @Override
            public void onBulkInsert(MeetingTask... task) {
                updateSchedule();
            }

            @Override
            public void onClear() {
                updateSchedule();
            }
        });
        getCountdown().addListener(new TimerCountdownListener() {

            @Override
            public void onStart(MeetingTask task) {
                super.onStart(task);
                updateTask(task);
                if(lastTask!=null) {
                    lastTask.useBuzzerProperty().removeListener(buzzerListener);
                    lastTask.countdownProperty().removeListener(countdownDirectListener);
                }
                task.useBuzzerProperty().addListener(buzzerListener);
                task.countdownProperty().addListener(countdownDirectListener);
                lastTask = task;
            }

            @Override
            public void onEnforceTime(int time) {
                super.onEnforceTime(time);
                updateTime();
            }

            @Override
            public void onPause() {
                super.onPause();
                updatePause(true);
            }

            @Override
            public void onResume() {
                super.onResume();
                updatePause(false);
            }

            @Override
            public void onTimeManipulate(int totalAdded, int added) {
                super.onTimeManipulate(totalAdded, added);
                updateTimeAdded(totalAdded);
            }

            @Override
            public void onStop() {
                super.onStop();
                if(lastTask!=null) {
                    lastTask.useBuzzerProperty().removeListener(buzzerListener);
                    lastTask.countdownProperty().removeListener(countdownDirectListener);
                }
                updateTask(null);
            }
        });
        recordControl.addListener(new DefaultVoiceRecorderRecordControlListener() {
            @Override
            public void onStart() {
                super.onStart();
                bean.setRecording(true);
            }

            @Override
            public void onStop() {
                super.onStop();
                bean.setRecording(false);
            }
        });
        updateSchedule();
        updateTask(getCountdown().getTask());
        updatePause(getCountdown().isPause());
        updateTimeAdded(getCountdown().getAddedTime());
        lastTask = getCountdown().getTask();
        if(lastTask!=null) {
            lastTask.useBuzzerProperty().addListener(buzzerListener);
            lastTask.countdownProperty().addListener(countdownDirectListener);
        }
    }

    private void updateTimeAdded(int totalAdded) {
        bean.setAddedTime(totalAdded);
        saveData();
    }

    private void updateBuzzer(boolean isActive) {
        bean.getBean().setUseBuzzer(isActive);
        saveData();
    }


    private void updateDirect(boolean isDown) {
        bean.getBean().setCountdownDown(isDown);
        saveData();
    }

    private void updateTask(MeetingTask task) {
        if(task==null) {
            bean.setBean(null);
        } else {
            bean.setBean(new OfflineMeetingBean(task));
        }
        bean.setPause(false);
        bean.setAddedTime(getCountdown().getAddedTime());
        if(task!=null) {
            bean.setLastStartTime(System.currentTimeMillis());
            bean.setLastTime(task.getTimeInSeconds());
        } else {
            bean.setLastStartTime(0);
            bean.setLastTime(0);
        }
        saveData();
    }

    private void updateTime() {
        bean.setLastStartTime(System.currentTimeMillis());
        bean.setLastTime(getCountdown().getTime());
    }

    private void updatePause(boolean isPause) {
        bean.setPause(isPause);
        bean.setLastStartTime(System.currentTimeMillis());
        bean.setLastTime(getCountdown().getTime()-getCountdown().getAddedTime());
        saveData();
    }

    private void updateSchedule() {
        new Thread(()->{
            List<MeetingTask> list = getSchedule().getList();
            List<OfflineMeetingBean> offlineList = new ArrayList<>();
            for(MeetingTask task:list) {
                offlineList.add(new OfflineMeetingBean(task));
            }
            bean.setSchedule(offlineList);
            saveData();
        }).start();
    }

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private void saveData() {
        executor.execute(()->{
            File dest = FileManager.getScheduleFile();
            FileUtils.writeToFile(dest, getDataToSave());
        });
    }

    private String getDataToSave() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(bean);
    }

    private Schedule getSchedule() {
        return schedule;
    }

    private Countdown getCountdown() {
        return countdown;
    }
}
