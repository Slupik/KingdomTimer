package jw.kingdom.hall.kingdomtimer.domain.record.voice;

import jw.kingdom.hall.kingdomtimer.recorder.RecordListener;

/**
 * All rights reserved & copyright ©
 */
public interface RecordControl {
    void start();
    void stop();
    void pause();
    void resume();

    void addListener(RecordListener recordListener);
    void removeListener(RecordListener recordListener);

    void addListener(RecordControlListener listener);
    void removeListener(RecordControlListener listener);

    boolean isRecording();
}
