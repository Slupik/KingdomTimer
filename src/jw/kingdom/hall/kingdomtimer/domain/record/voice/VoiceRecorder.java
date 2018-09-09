package jw.kingdom.hall.kingdomtimer.domain.record.voice;

import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.recorder.xt.XtRecorder;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class VoiceRecorder {
    private Recorder recorder;
    private boolean isPause = false;
    private boolean start = false;
    private final List<Listener> listeners = new ArrayList<>();

    public void start(){
        if(!start) {
            start = true;
            recorder.onStart();
            setPause(false);
            for(Listener listener:listeners) {
                listener.onStart();
            }
        }
    }

    public void stop(){
        recorder.onStop();
        setPause(false);
        start = false;
        for(Listener listener:listeners) {
            listener.onStop();
        }
    }

    public void pauseChange(){
        isPause = !isPause;
        recorder.setPause(isPause);
    }

    public void setPause(boolean isPause){
        this.isPause = isPause;
        recorder.setPause(isPause);
        for(Listener listener:listeners) {
            listener.onPause(isPause);
        }
    }

    public void addDisplay(Recorder.Display display) {
        recorder.addDisplay(display);
    }
    public void removeDisplay(Recorder.Display display) {
        recorder.removeDisplay(display);
    }

    public void addListener(Recorder.Listener listener) {
        recorder.addListener(listener);
    }
    public void removeListener(Recorder.Listener listener) {
        recorder.removeListener(listener);
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private static VoiceRecorder instance;
    public static VoiceRecorder getInstance() {
        if(null == instance) {
            instance = new VoiceRecorder();
        }
        return instance;
    }
    private VoiceRecorder(){
        DefaultAudioSettingsBean bean = new DefaultAudioSettingsBean();
        recorder = new XtRecorder(bean);
    }

    public interface Listener {
        void onStart();
        void onPause(boolean isPause);
        void onStop();
    }
}
