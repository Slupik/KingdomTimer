package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.record;

import jw.kingdom.hall.kingdomtimer.entity.task.TaskType;
import jw.kingdom.hall.kingdomtimer.javafx.GuiTimeListener;
import jw.kingdom.hall.kingdomtimer.javafx.entity.task.TaskFxBean;
import jw.kingdom.hall.kingdomtimer.javafx.mapper.MapperPojoToFxTask;
import jw.kingdom.hall.kingdomtimer.javafx.utils.PlatformUtils;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.usecase.task.pojo.TaskPOJO;
import jw.kingdom.hall.kingdomtimer.usecase.time.countdown.CountdownController;
import jw.kingdom.hall.kingdomtimer.usecase.time.schedule.ScheduleController;

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
        input.getSchedule().addListener(new GuiTimeListener() {
            @Override
            public void onMeetingStart() {
                startedRecord = false;
            }

            @Override
            public void onStart(TaskPOJO pojo) {
                TaskFxBean task = new MapperPojoToFxTask().map(pojo);
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
            public void onMeetingEnd() {
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
