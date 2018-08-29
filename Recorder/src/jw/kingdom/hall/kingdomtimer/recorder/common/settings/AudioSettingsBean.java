package jw.kingdom.hall.kingdomtimer.recorder.common.settings;

/**
 * All rights reserved & copyright ©
 */
public interface AudioSettingsBean {
    String getDestinationFolder();
    boolean isReadDefaultMixSettings();
    int getRate();
    String getSample();
    int getInputs();
    int getSampleSize();

    boolean isToSelectDefaultDevice();
    String getDeviceName();
    String getServiceSetupID();
}
