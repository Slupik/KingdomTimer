package jw.kingdom.hall.kingdomtimer.config;

import java.io.File;
import java.io.IOException;

/**
 * All rights reserved & copyright ©
 */
public interface Config {
    void loadLocalData(String data);
    void loadParent(Config parent);
    void save(File file) throws IOException;
    void fillWith(Config source);



    String getSpeakerScreen();
    boolean isEnabledGleaming();
    boolean isEnabledShowMultimedia();

    String getRecordDestPath();
    boolean isEnabledAutopilot();

    String getMultimediaScreen();
    int getMinRefreshRate();
    int getWarningRefreshRate();
    int getDefaultRefreshRate();

    boolean isDirectDown();



    void setSpeakerScreen(String screen);
    void setEnabledGleaming(boolean isGleaming);
    void setEnabledShowMultimedia(boolean isShow);

    void setRecordDestPath(String path);
    void setEnabledAutopilot(boolean isAutopilot);

    void setMultimediaScreen(String multiScreen);
    void setMinRefreshRate(int minRefRate);
    void setWarningRefreshRate(int warningRefRate);
    void setDefaultRefreshRate(int defaultRefRate);

    void setDirectDown(boolean isDirectDown);
}
