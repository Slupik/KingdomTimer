package jw.kingdom.hall.kingdomtimer.recorder;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface Recorder {
    void onStart();
    void onStop();
    void setPause(boolean isPause);

    void addDisplay(Display display);
    void removeDisplay(Display display);

    interface Display {
        void onNewTime(int seconds);
    }

    void addListener(Listener listener);
    void removeListener(Listener listener);
    interface Listener {
        void onStop();
        void onPause();
        void onResume();
        void onStart();
    }
}
