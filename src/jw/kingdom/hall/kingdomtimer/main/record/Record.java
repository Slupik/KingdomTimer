package jw.kingdom.hall.kingdomtimer.main.record;

import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * All rights reserved & copyright ©
 */
public class Record implements Recorder {

    public Record(){
        init();
    }

    private void init() {
        VoiceRecorder.getInstance();
    }

    @Override
    public void onStart() {
        VoiceRecorder.getInstance().start();
    }

    @Override
    public void onStop() {
        VoiceRecorder.getInstance().stop();
    }

    @Override
    public void setPause(boolean isPause) {
        VoiceRecorder.getInstance().setPause(isPause);
    }

    @Override
    public void addDisplay(Display display) {
        VoiceRecorder.getInstance().addDisplay(display);
    }

    @Override
    public void removeDisplay(Display display) {
        VoiceRecorder.getInstance().removeDisplay(display);
    }

    @Override
    public void addListener(Listener listener) {
        VoiceRecorder.getInstance().addListener(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        VoiceRecorder.getInstance().removeListener(listener);
    }
}
