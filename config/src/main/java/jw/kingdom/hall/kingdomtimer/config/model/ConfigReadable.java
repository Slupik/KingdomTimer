package jw.kingdom.hall.kingdomtimer.config.model;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface ConfigReadable {
    String getSpeakerScreen();
    boolean isEnabledGleaming();
    boolean isVisibleSpeakerScreen();
    boolean isEnabledShowMultimedia();

    String getRecordDestPath();
    boolean isEnabledAutopilot();
    boolean isAutoSeparate();
    String getRawFileNameBackup();
    String getRawFileNameBackupGroups();
    String getRawFileNameFinal();
    String getRawFileNameFinalGroups();
    int getOutputBitRate();

    String getMultimediaScreen();
    int getMinRefreshRate();
    int getWarningRefreshRate();
    int getDefaultRefreshRate();
    int getActualRefreshRate();

    boolean isDirectDown();
    int getTimeToEvaluate();
    int getMeetingTime();

    String[] getIpOfHardwareTimersControlledByHttp();
}
