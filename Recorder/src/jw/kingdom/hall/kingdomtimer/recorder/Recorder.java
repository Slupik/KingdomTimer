package jw.kingdom.hall.kingdomtimer.recorder;

/**
 * All rights reserved & copyright ©
 */
public interface Recorder {
    void onStart();
    void onStop();
    void setPause(boolean isPause);

    void addListener(Listener listener);
    void removeListener(Listener listener);
    interface Listener {
        void onNewTime(int seconds);
    }
}
