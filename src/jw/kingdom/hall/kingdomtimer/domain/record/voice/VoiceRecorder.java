package jw.kingdom.hall.kingdomtimer.domain.record.voice;

import jw.kingdom.hall.kingdomtimer.app.javafx.utils.PlatformUtils;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.countdown.Countdown;
import jw.kingdom.hall.kingdomtimer.domain.schedule.Schedule;
import jw.kingdom.hall.kingdomtimer.recorder.RecordListener;
import jw.kingdom.hall.kingdomtimer.recorder.RecordListenerProxy;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.recorder.xt.XtRecorder;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class VoiceRecorder implements RecordControl {
    private Recorder recorder;
    private boolean start = false;
    private final List<RecordControlListener> listeners = new ArrayList<>();
    private int recordingThreads = 0;

    @Override
    public void start(){
        if(!start) {
            start = true;
            PlatformUtils.runOnUiThread(()-> recorder.onStart());
            for(RecordControlListener listener:listeners) {
                listener.onStart();
            }
        }
    }

    @Override
    public void stop(){
        PlatformUtils.runOnUiThread(()-> recorder.onStop());
        start = false;
        for(RecordControlListener listener:listeners) {
            listener.onStop();
        }
    }

    @Override
    public void pause() {
        PlatformUtils.runOnUiThread(()-> recorder.setPause(true));
        for(RecordControlListener listener:listeners) {
            listener.onPause();
        }
    }

    @Override
    public void resume() {
        PlatformUtils.runOnUiThread(()-> recorder.setPause(false));
        for(RecordControlListener listener:listeners) {
            listener.onResume();
        }
    }

    @Override
    public void addListener(RecordListener recordListener) {
        recorder.addListener(recordListener);
    }
    @Override
    public void removeListener(RecordListener recordListener) {
        recorder.removeListener(recordListener);
    }

    @Override
    public void addListener(RecordControlListener listener) {
        listeners.add(listener);
    }
    @Override
    public void removeListener(RecordControlListener listener) {
        listeners.remove(listener);
    }

    private static VoiceRecorder instance;
    public static VoiceRecorder getInstance(Config config, Schedule schedule, Countdown countdown) {
        if(null == instance) {
            instance = new VoiceRecorder(config, schedule, countdown);
        }
        return instance;
    }
    private VoiceRecorder(Config config, Schedule schedule, Countdown countdown){
        DefaultAudioSettingsBean bean = new DefaultAudioSettingsBean(config, schedule, countdown);
        recorder = new XtRecorder(bean);
        addListener(new RecordListenerProxy() {

            @Override
            public void onStart() {
                super.onStart();
                recordingThreads++;
            }

            @Override
            public void onEnd() {
                super.onEnd();
                recordingThreads--;
            }
        });
    }

    @Override
    public boolean isRecording() {
        return recordingThreads>0;
    }
}
