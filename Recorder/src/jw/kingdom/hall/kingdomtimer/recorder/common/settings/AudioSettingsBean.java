package jw.kingdom.hall.kingdomtimer.recorder.common.settings;

import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;

/**
 * All rights reserved & copyright ©
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
