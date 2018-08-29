package jw.kingdom.hall.kingdomtimer.data.config;

import jw.kingdom.hall.kingdomtimer.config.model.Config;

import java.io.File;
import java.io.IOException;

/**
 * All rights reserved & copyright ©
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
    public String getSpeakerScreen() {
        return getConfig().getSpeakerScreen();
    }

    @Override
    public boolean isEnabledGleaming() {
        return getConfig().isEnabledGleaming();
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
}