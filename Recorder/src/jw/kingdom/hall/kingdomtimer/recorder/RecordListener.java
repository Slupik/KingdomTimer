package jw.kingdom.hall.kingdomtimer.recorder;

/**
 * All rights reserved & copyright ©
 */
public interface RecordListener {
    void onStart();
    void onStop();
    void onEnd();
    void onNewTime(int seconds);
}
