package jw.kingdom.hall.kingdomtimer.domain.record.voice;

import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;
import jw.kingdom.hall.kingdomtimer.recorder.common.settings.DefaultAudioSettingsBean;
import jw.kingdom.hall.kingdomtimer.recorder.xt.XtRecorder;

/**
 * All rights reserved & copyright ©
 */
public class VoiceRecorder {
    private Recorder recorder;
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

    public void addListener(Recorder.Listener listener) {
        recorder.addListener(listener);
    }
    public void removeListener(Recorder.Listener listener) {
        recorder.removeListener(listener);
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
        bean.setDestinationFolder(AppConfig.getInstance().getRecordDestPath());
        recorder = new XtRecorder(bean);
    }
}
