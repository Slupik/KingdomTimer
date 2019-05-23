package jw.kingdom.hall.kingdomtimer.recorder;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface Recorder {
    void onStart();
    void onStop();
    void setPause(boolean isPause);

    void addListener(RecordListener recordListener);
    void removeListener(RecordListener recordListener);
}
