package jw.kingdom.hall.kingdomtimer.config.model;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface ConfigEditable {
    void setSpeakerScreen(String screen);
    void setEnabledGleaming(boolean isGleaming);
    void setVisibilitySpeakerScreen(boolean isVisible);
    void setEnabledShowMultimedia(boolean isShow);

    void setRecordDestPath(String path);
    void setEnabledAutopilot(boolean isAutopilot);
    void setEnabledAutoSeparate(boolean isEnabled);
    void setRawFileNameBackup(String rawFileNameBackup);
    void setRawFileNameBackupGroups(String rawFileNameBackupGroups);
    void setRawFileNameFinal(String rawFileNameFinal);
    void setRawFileNameFinalGroups(String rawFileNameFinalGroups);

    void setMultimediaScreen(String multiScreen);
    void setMinRefreshRate(int minRefRate);
    void setWarningRefreshRate(int warningRefRate);
    void setDefaultRefreshRate(int defaultRefRate);
    void setActualRefreshRate(int actualRefRate);

    void setDirectDown(boolean isDirectDown);
}
