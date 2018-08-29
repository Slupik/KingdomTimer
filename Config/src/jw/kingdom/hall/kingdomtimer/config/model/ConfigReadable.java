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

    String getMultimediaScreen();
    int getMinRefreshRate();
    int getWarningRefreshRate();
    int getDefaultRefreshRate();
    int getActualRefreshRate();

    boolean isDirectDown();
}
