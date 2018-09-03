package jw.kingdom.hall.kingdomtimer.recorder.common.settings;

import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface AudioSettingsBean {
    FileRecordCreator getPaths();
    boolean isReadDefaultMixSettings();
    int getRate();
    String getSample();
    int getInputs();
    int getSampleSize();

    boolean isToSelectDefaultDevice();
    String getDeviceName();
    String getServiceSetupID();
}
