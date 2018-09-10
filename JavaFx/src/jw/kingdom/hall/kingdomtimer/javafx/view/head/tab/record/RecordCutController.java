package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.record;

import jw.kingdom.hall.kingdomtimer.entity.task.Task;
import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.entity.time.countdown.CountdownListener;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleController;
import jw.kingdom.hall.kingdomtimer.entity.time.schedule.ScheduleListener;
import jw.kingdom.hall.kingdomtimer.javafx.utils.PlatformUtils;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * All rights reserved & copyright ©
 */
class RecordCutController {

    private final Input input;
    private boolean startedRecord = false;
    private TaskType lastType = null;

    RecordCutController(Input input) {
        this.input = input;
        init();
    }

    private void init() {
        input.getSchedule().addListener(new ScheduleListener() {
            @Override
            public void onFirstTaskUse() {
                super.onFirstTaskUse();
                startedRecord = false;
            }
        });
        input.getCountdown().addListener(new CountdownListener() {
            @Override
            public void onTaskStart(Task task) {
                super.onTaskStart(task);
                if(input.isAutoPilotOn() && !startedRecord) {
                    startedRecord = true;
                    PlatformUtils.runOnUiThread(()-> input.getRecorder().onStart());

                } else if(input.isAutoPilotOn() && input.isAutoSeparateOn() &&
                        (lastType==null || lastType!=task.getType())) {
                    PlatformUtils.runOnUiThread(()->{
                        input.getRecorder().onStop();
                        input.getRecorder().onStart();
                    });
                }

                lastType = task.getType();
            }

            @Override
            protected void onStop() {
                super.onStop();
                if(input.getSchedule().getTasks().size()==0) {
                    PlatformUtils.runOnUiThread(()-> input.getRecorder().onStop());
                    startedRecord = false;
                }
            }
        });
    }

    interface Input {
        Recorder getRecorder();
        ScheduleController getSchedule();
        CountdownController getCountdown();
        boolean isAutoPilotOn();
        boolean isAutoSeparateOn();
    }
}
