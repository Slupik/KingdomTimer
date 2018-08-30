package jw.kingdom.hall.kingdomtimer.config.model;

/**
 * All rights reserved & copyright ©
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

    String getMultimediaScreen();
    int getMinRefreshRate();
    int getWarningRefreshRate();
    int getDefaultRefreshRate();
    int getActualRefreshRate();

    boolean isDirectDown();
}
