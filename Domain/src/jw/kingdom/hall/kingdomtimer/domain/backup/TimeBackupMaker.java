package jw.kingdom.hall.kingdomtimer.domain.backup;

import com.google.gson.GsonBuilder;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.OfflineMeetingBean;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.TimeBackupBean;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.countdown.CountdownListenerProxy;
import jw.kingdom.hall.kingdomtimer.domain.file.BackupFileController;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.DefaultVoiceRecorderRecordControlListener;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.schedule.MeetingScheduleListener;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.utils.FileUtils;

import java.beans.PropertyChangeListener;
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
    private final BackupFileController fileController;
    private TimeBackupBean bean = new TimeBackupBean();
    private PropertyChangeListener listener = evt -> {
        if(evt.getPropertyName().equals(TaskBean.PropertyName.COUNTDOWN_DOWN)) {
            updateDirect((Boolean) evt.getNewValue());
        }
        if(evt.getPropertyName().equals(TaskBean.PropertyName.USE_BUZZER)) {
            updateBuzzer((Boolean) evt.getNewValue());
        }
    };
    private TaskBean lastTask = null;

    TimeBackupMaker(RecordControl recordControl, Schedule schedule, Countdown countdown, BackupFileController fileController){
        this.schedule = schedule;
        this.countdown = countdown;
        this.fileController = fileController;
        getSchedule().addListener(new MeetingScheduleListener() {

            @Override
            public void onRemove(TaskBean task) {
                updateSchedule();
            }

            @Override
            public void onInsert(TaskBean task) {
                updateSchedule();
            }

            @Override
            public void onBulkInsert(TaskBean... task) {
                updateSchedule();
            }

            @Override
            public void onClear() {
                updateSchedule();
            }
        });
        getCountdown().addListener(new CountdownListenerProxy() {

            @Override
            public void onStart(TaskBean task) {
                super.onStart(task);
                updateTask(task);
                if(lastTask!=null) {
                    lastTask.removePropertyChangeListener(listener);
                }
                task.addPropertyChangeListener(listener);
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
                    lastTask.removePropertyChangeListener(listener);
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
            lastTask.addPropertyChangeListener(listener);
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

    private void updateTask(TaskBean task) {
        if(task==null) {
            bean.setBean(null);
        } else {
            bean.setBean(new OfflineMeetingBean(task));
        }
        bean.setPause(false);
        bean.setAddedTime(getCountdown().getAddedTime());
        if(task!=null) {
            bean.setLastStartTime(System.currentTimeMillis());
            bean.setLastTime(task.getTime());
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
            List<TaskBean> list = getSchedule().getList();
            List<OfflineMeetingBean> offlineList = new ArrayList<>();
            for(TaskBean task:list) {
                offlineList.add(new OfflineMeetingBean(task));
            }
            bean.setSchedule(offlineList);
            saveData();
        }).start();
    }

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private void saveData() {
        executor.execute(()->{
            File dest = fileController.getTimeFile();
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
