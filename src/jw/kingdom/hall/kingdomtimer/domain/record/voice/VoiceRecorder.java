package jw.kingdom.hall.kingdomtimer.domain.record.voice;

import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.recorder.xt.XtRecorder;

/**
 * All rights reserved & copyright ©
 */
public class VoiceRecorder {
    private Recorder recorder = new XtRecorder();
    private boolean isPause = false;

    public void start(){
        recorder.onStart();
        setPause(false);
    }

    public void stop(){
        recorder.onStop();
        setPause(false);
    }

    public void pauseChange(){
        isPause = !isPause;
        recorder.setPause(isPause);
    }

    public void setPause(boolean isPause){
        this.isPause = isPause;
        recorder.setPause(isPause);
    }

    private static VoiceRecorder instance;
    public static VoiceRecorder getInstance() {
        if(null == instance) {
            instance = new VoiceRecorder();
        }
        return instance;
    }
    private VoiceRecorder(){}
}
