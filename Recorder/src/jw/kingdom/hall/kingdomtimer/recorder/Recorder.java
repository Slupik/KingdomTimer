package jw.kingdom.hall.kingdomtimer.recorder;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
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
