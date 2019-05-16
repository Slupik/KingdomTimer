package jw.kingdom.hall.kingdomtimer.data.file.config;

import jw.kingdom.hall.kingdomtimer.config.model.Config;

import java.io.File;
import java.io.IOException;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
abstract class ConfigBase implements Config {

    protected abstract Config getConfig();

    @Override
    public void save(File file) throws IOException {
        getConfig().save(file);
    }
    public abstract void save() throws IOException;
    public void autoSave() {
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSpeakerScreen(String screen) {
        getConfig().setSpeakerScreen(screen);
        autoSave();
    }

    @Override
    public void setEnabledGleaming(boolean isGleaming) {
        getConfig().setEnabledGleaming(isGleaming);
        autoSave();
    }

    @Override
    public void setVisibilitySpeakerScreen(boolean isVisible) {
        getConfig().setVisibilitySpeakerScreen(isVisible);
        autoSave();
    }

    @Override
    public void setEnabledShowMultimedia(boolean isShow) {
        getConfig().setEnabledShowMultimedia(isShow);
        autoSave();
    }

    @Override
    public void setRecordDestPath(String path) {
        getConfig().setRecordDestPath(path);
        autoSave();
    }

    @Override
    public void setEnabledAutopilot(boolean isAutopilot) {
        getConfig().setEnabledAutopilot(isAutopilot);
        autoSave();
    }

    @Override
    public void setMultimediaScreen(String multiScreen) {
        getConfig().setMultimediaScreen(multiScreen);
        autoSave();
    }

    @Override
    public void setMinRefreshRate(int minRefRate) {
        getConfig().setMinRefreshRate(minRefRate);
        autoSave();
    }

    @Override
    public void setWarningRefreshRate(int warningRefRate) {
        getConfig().setWarningRefreshRate(warningRefRate);
        autoSave();
    }

    @Override
    public void setDefaultRefreshRate(int defaultRefRate) {
        getConfig().setDefaultRefreshRate(defaultRefRate);
        autoSave();
    }

    @Override
    public void setActualRefreshRate(int actualRefRate) {
        getConfig().setActualRefreshRate(actualRefRate);
        autoSave();
    }

    @Override
    public void setDirectDown(boolean isDirectDown) {
        getConfig().setDirectDown(isDirectDown);
        autoSave();
    }

    @Override
    public void setEnabledAutoSeparate(boolean isEnabled) {
        getConfig().setEnabledAutoSeparate(isEnabled);
        autoSave();
    }

    @Override
    public void setRawFileNameBackup(String rawFileNameBackup) {
        getConfig().setRawFileNameBackup(rawFileNameBackup);
        autoSave();
    }

    @Override
    public void setRawFileNameBackupGroups(String rawFileNameBackupGroups) {
        getConfig().setRawFileNameBackupGroups(rawFileNameBackupGroups);
        autoSave();
    }

    @Override
    public void setRawFileNameFinal(String rawFileNameFinal) {
        getConfig().setRawFileNameFinal(rawFileNameFinal);
        autoSave();
    }

    @Override
    public void setRawFileNameFinalGroups(String rawFileNameFinalGroups) {
        getConfig().setRawFileNameFinalGroups(rawFileNameFinalGroups);
        autoSave();
    }

    @Override
    public void setTimeToEvaluate(int seconds) {
        getConfig().setTimeToEvaluate(seconds);
        autoSave();
    }

    @Override
    public void setMeetingTime(int seconds) {
        getConfig().setMeetingTime(seconds);
        autoSave();
    }

    @Override
    public String getSpeakerScreen() {
        return getConfig().getSpeakerScreen();
    }

    @Override
    public boolean isEnabledGleaming() {
        return getConfig().isEnabledGleaming();
    }

    @Override
    public boolean isVisibleSpeakerScreen() {
        return getConfig().isVisibleSpeakerScreen();
    }

    @Override
    public boolean isEnabledShowMultimedia() {
        return getConfig().isEnabledShowMultimedia();
    }

    @Override
    public String getRecordDestPath() {
        return getConfig().getRecordDestPath();
    }

    @Override
    public boolean isEnabledAutopilot() {
        return getConfig().isEnabledAutopilot();
    }

    @Override
    public String getMultimediaScreen() {
        return getConfig().getMultimediaScreen();
    }

    @Override
    public int getMinRefreshRate() {
        return getConfig().getMinRefreshRate();
    }

    @Override
    public int getWarningRefreshRate() {
        return getConfig().getWarningRefreshRate();
    }

    @Override
    public int getDefaultRefreshRate() {
        return getConfig().getDefaultRefreshRate();
    }

    @Override
    public int getActualRefreshRate() {
        return getConfig().getActualRefreshRate();
    }

    @Override
    public boolean isDirectDown() {
        return getConfig().isDirectDown();
    }

    @Override
    public boolean isAutoSeparate() {
        return getConfig().isAutoSeparate();
    }

    @Override
    public String getRawFileNameBackup() {
        return getConfig().getRawFileNameBackup();
    }

    @Override
    public String getRawFileNameBackupGroups() {
        return getConfig().getRawFileNameBackupGroups();
    }

    @Override
    public String getRawFileNameFinal() {
        return getConfig().getRawFileNameFinal();
    }

    @Override
    public String getRawFileNameFinalGroups() {
        return getConfig().getRawFileNameFinalGroups();
    }

    @Override
    public int getTimeToEvaluate() {
        return getConfig().getTimeToEvaluate();
    }

    @Override
    public int getMeetingTime() {
        return getConfig().getMeetingTime();
    }

    @Override
    public String[] getIpOfHardwareTimersControlledByHttp() {
        return getConfig().getIpOfHardwareTimersControlledByHttp();
    }
}
