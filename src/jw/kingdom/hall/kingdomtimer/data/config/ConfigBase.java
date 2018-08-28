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

    @Override
    public void setSpeakerScreen(String screen) {
        getConfig().setSpeakerScreen(screen);
    }

    @Override
    public void setEnabledGleaming(boolean isGleaming) {
        getConfig().setEnabledGleaming(isGleaming);
    }

    @Override
    public void setEnabledShowMultimedia(boolean isShow) {
        getConfig().setEnabledShowMultimedia(isShow);
    }

    @Override
    public void setRecordDestPath(String path) {
        getConfig().setRecordDestPath(path);
    }

    @Override
    public void setEnabledAutopilot(boolean isAutopilot) {
        getConfig().setEnabledAutopilot(isAutopilot);
    }

    @Override
    public void setMultimediaScreen(String multiScreen) {
        getConfig().setMultimediaScreen(multiScreen);
    }

    @Override
    public void setMinRefreshRate(int minRefRate) {
        getConfig().setMinRefreshRate(minRefRate);
    }

    @Override
    public void setWarningRefreshRate(int warningRefRate) {
        getConfig().setWarningRefreshRate(warningRefRate);
    }

    @Override
    public void setDefaultRefreshRate(int defaultRefRate) {
        getConfig().setDefaultRefreshRate(defaultRefRate);
    }

    @Override
    public void setDirectDown(boolean isDirectDown) {
        getConfig().setDirectDown(isDirectDown);
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
    public boolean isDirectDown() {
        return getConfig().isDirectDown();
    }
}
