package jw.kingdom.hall.kingdomtimer.config.utils;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.model.ConfigWriteable;

import java.io.File;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class DefaultConfig implements ConfigWriteable {
    @Override
    public void loadLocalData(String data) {}
    @Override
    public void loadParent(Config parent) {}
    @Override
    public void save(File file) {}
    @Override
    public void fillWith(Config source) {}

    @Override
    public String getSpeakerScreen() {
        return null;
    }

    @Override
    public boolean isEnabledGleaming() {
        return true;
    }

    @Override
    public boolean isVisibleSpeakerScreen() {
        return true;
    }

    @Override
    public boolean isEnabledShowMultimedia() {
        return true;
    }

    @Override
    public String getRecordDestPath() {
        return "";
    }

    @Override
    public boolean isEnabledAutopilot() {
        return true;
    }

    @Override
    public boolean isAutoSeparate() {
        return false;
    }

    @Override
    public String getRawFileNameBackup() {
        return "BACKUP_{dluga_data}";
    }

    @Override
    public String getRawFileNameBackupGroups() {
        return "BACKUP_{dluga_data}_{blok}";
    }

    @Override
    public String getRawFileNameFinal() {
        return "{data}";
    }

    @Override
    public String getRawFileNameFinalGroups() {
        return "{data}_{blok}";
    }

    @Override
    public int getOutputBitRate() {
        return 128000;
    }

    @Override
    public String getMultimediaScreen() {
        return null;
    }

    @Override
    public int getMinRefreshRate() {
        return 100;
    }

    @Override
    public int getWarningRefreshRate() {
        return 250;
    }

    @Override
    public int getDefaultRefreshRate() {
        return 500;
    }

    @Override
    public int getActualRefreshRate() {
        return 500;
    }

    @Override
    public boolean isDirectDown() {
        return true;
    }

    @Override
    public int getTimeToEvaluate() {
        return -1;
    }

    @Override
    public int getMeetingTime() {
        return 95*60;
    }

    @Override
    public String[] getIpOfHardwareTimersControlledByHttp() {
        return new String[0];
    }


    @Override
    public void setSpeakerScreen(String screen) {}

    @Override
    public void setEnabledGleaming(boolean isGleaming) {}

    @Override
    public void setVisibilitySpeakerScreen(boolean isVisible) {}

    @Override
    public void setEnabledShowMultimedia(boolean isShow) {}

    @Override
    public void setRecordDestPath(String path) {}

    @Override
    public void setEnabledAutopilot(boolean isAutopilot) {}

    @Override
    public void setEnabledAutoSeparate(boolean isEnabled) {}

    @Override
    public void setRawFileNameBackup(String rawFileNameBackup) {}

    @Override
    public void setRawFileNameBackupGroups(String rawFileNameBackupGroups) {}

    @Override
    public void setRawFileNameFinal(String rawFileNameFinal) {}

    @Override
    public void setRawFileNameFinalGroups(String rawFileNameFinalGroups) {}

    @Override
    public void setMultimediaScreen(String multiScreen) {}

    @Override
    public void setMinRefreshRate(int minRefRate) {}

    @Override
    public void setWarningRefreshRate(int warningRefRate) {}

    @Override
    public void setDefaultRefreshRate(int defaultRefRate) {}

    @Override
    public void setActualRefreshRate(int defaultRefRate) {}

    @Override
    public void setDirectDown(boolean isDirectDown) {}

    @Override
    public void setTimeToEvaluate(int seconds) {}

    @Override
    public void setMeetingTime(int seconds) {}
}
