package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jw.kingdom.hall.kingdomtimer.config.ConfigUtils;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.model.ConfigWriteable;
import jw.kingdom.hall.kingdomtimer.config.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import static jw.kingdom.hall.kingdomtimer.config.ConfigUtils.BACKBONE;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class JsonConfig implements ConfigWriteable {
    private Config parent;
    private JsonConfigRoot config = new Gson().fromJson(BACKBONE, JsonConfigRoot.class);

    @Override
    public void loadLocalData(String data) {
        if(data==null || data.length()<3) {
            data = ConfigUtils.BACKBONE;
        }
        try {
            config = new Gson().fromJson(data, JsonConfigRoot.class);
        } catch (Exception ignore) {
            config = new Gson().fromJson(ConfigUtils.BACKBONE, JsonConfigRoot.class);
        }
        if(null == parent) {
            config.applyParentConfig(ConfigUtils.DEFAULT);
        } else {
            config.applyParentConfig(parent);
        }
    }

    @Override
    public void loadParent(Config parent) {
        this.parent = parent;
        config.applyParentConfig(parent);
    }

    @Override
    public void save(File file) throws IOException {
        if(file.exists()) file.delete();
        file.createNewFile();
        FileUtils.writeToFile(file, this.toString());
    }

    @Override
    public void fillWith(Config source) {
        setSpeakerScreen(source.getSpeakerScreen());
        setEnabledGleaming(source.isEnabledGleaming());
        setEnabledShowMultimedia(source.isEnabledShowMultimedia());

        setRecordDestPath(source.getRecordDestPath());
        setEnabledAutopilot(source.isEnabledAutopilot());

        setMultimediaScreen(source.getMultimediaScreen());
        setMinRefreshRate(source.getMinRefreshRate());
        setWarningRefreshRate(source.getWarningRefreshRate());
        setActualRefreshRate(source.getActualRefreshRate());

        setDirectDown(source.isDirectDown());
    }

    @Override
    public String toString(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create()
                .toJson(config);
    }


    @Override
    public String getSpeakerScreen() {
        return config.getSpeaker().getScreen();
    }

    @Override
    public boolean isEnabledGleaming() {
        try {
            return config.getSpeaker().isGleaming();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.isEnabledGleaming();
        }
    }

    @Override
    public boolean isVisibleSpeakerScreen() {
        try {
            return config.getSpeaker().isVisible();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.isVisibleSpeakerScreen();
        }
    }

    @Override
    public boolean isEnabledShowMultimedia() {
        try {
            return config.getSpeaker().isShowMulti();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.isEnabledShowMultimedia();
        }
    }



    @Override
    public String getRecordDestPath() {
        return config.getRecording().getPath();
    }

    @Override
    public boolean isEnabledAutopilot() {
        try {
            return config.getRecording().getAutopilot();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.isEnabledAutopilot();
        }
    }

    @Override
    public boolean isAutoSeparate() {
        try {
            return config.getRecording().getAutoSeparate();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.isAutoSeparate();
        }
    }

    @Override
    public String getRawFileNameBackup() {
        try {
            return config.getRecording().getRawFileNameBackup();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getRawFileNameBackup();
        }
    }

    @Override
    public String getRawFileNameBackupGroups() {
        try {
            return config.getRecording().getRawFileNameBackupGroups();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getRawFileNameBackupGroups();
        }
    }

    @Override
    public String getRawFileNameFinal() {
        try {
            return config.getRecording().getRawFileNameFinal();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getRawFileNameFinal();
        }
    }

    @Override
    public String getRawFileNameFinalGroups() {
        try {
            return config.getRecording().getRawFileNameFinalGroups();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getRawFileNameFinalGroups();
        }
    }


    @Override
    public String getMultimediaScreen() {
        return config.getMultimedia().getScreen();
    }

    @Override
    public int getMinRefreshRate() {
        try {
            return config.getMultimedia().getMinRefreshRate();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getMinRefreshRate();
        }
    }

    @Override
    public int getWarningRefreshRate() {
        try {
            return config.getMultimedia().getWarningRefreshRate();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getWarningRefreshRate();
        }
    }

    @Override
    public int getDefaultRefreshRate() {
        try {
            return config.getMultimedia().getDefaultRefreshRate();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getDefaultRefreshRate();
        }
    }

    @Override
    public int getActualRefreshRate() {
        try {
            return config.getMultimedia().getActualRefreshRate();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getActualRefreshRate();
        }
    }



    @Override
    public boolean isDirectDown() {
        try {
            return config.getCountdown().getCountdownDown();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public int getTimeToEvaluate() {
        try {
            return config.getCountdown().getTimeToEvaluate();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getTimeToEvaluate();
        }
    }

    @Override
    public String[] getIpOfHardwareTimersControlledByHttp() {
        try {
            return config.getHardwareDisplays().getControlledByHttp();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigUtils.DEFAULT.getIpOfHardwareTimersControlledByHttp();
        }
    }

    @Override
    public void setSpeakerScreen(String screen) {
        if(config.getSpeaker()!=null) {
            config.getSpeaker().setScreen(screen);
        }
    }

    @Override
    public void setEnabledGleaming(boolean isGleaming) {
        if(config.getSpeaker()!=null) {
            config.getSpeaker().setGleaming(isGleaming);
        }
    }

    @Override
    public void setVisibilitySpeakerScreen(boolean isVisible) {
        if(config.getSpeaker()!=null) {
            config.getSpeaker().setVisibility(isVisible);
        }
    }

    @Override
    public void setEnabledShowMultimedia(boolean isShow) {
        if(config.getSpeaker()!=null) {
            config.getSpeaker().setShowMulti(isShow);
        }
    }

    @Override
    public void setRecordDestPath(String path) {
        if(config.getRecording()!=null) {
            config.getRecording().setPath(path);
        }
    }

    @Override
    public void setEnabledAutopilot(boolean isAutopilot) {
        if(config.getRecording()!=null) {
            config.getRecording().setAutopilot(isAutopilot);
        }
    }

    @Override
    public void setEnabledAutoSeparate(boolean isEnabled) {
        if(config.getRecording()!=null) {
            config.getRecording().setAutoSeparate(isEnabled);
        }
    }

    @Override
    public void setRawFileNameBackup(String rawFileNameBackup) {
        if(config.getRecording()!=null) {
            config.getRecording().setRawFileNameBackup(rawFileNameBackup);
        }
    }

    @Override
    public void setRawFileNameBackupGroups(String rawFileNameBackupGroups) {
        if(config.getRecording()!=null) {
            config.getRecording().setRawFileNameBackupGroups(rawFileNameBackupGroups);
        }
    }

    @Override
    public void setRawFileNameFinal(String rawFileNameFinal) {
        if(config.getRecording()!=null) {
            config.getRecording().setRawFileNameFinal(rawFileNameFinal);
        }
    }

    @Override
    public void setRawFileNameFinalGroups(String rawFileNameFinalGroups) {
        if(config.getRecording()!=null) {
            config.getRecording().setRawFileNameFinalGroups(rawFileNameFinalGroups);
        }
    }

    @Override
    public void setMultimediaScreen(String multiScreen) {
        if(config.getMultimedia()!=null) {
            config.getMultimedia().setScreen(multiScreen);
        }
    }

    @Override
    public void setMinRefreshRate(int minRefRate) {
        if(config.getMultimedia()!=null) {
            config.getMultimedia().setMinRefreshRate(minRefRate);
        }
    }

    @Override
    public void setWarningRefreshRate(int warningRefRate) {
        if(config.getMultimedia()!=null) {
            config.getMultimedia().setWarningRefreshRate(warningRefRate);
        }
    }

    @Override
    public void setDefaultRefreshRate(int defaultRefRate) {
        if(config.getMultimedia()!=null) {
            config.getMultimedia().setDefaultRefreshRate(defaultRefRate);
        }
    }

    @Override
    public void setActualRefreshRate(int actualRefRate) {
        if(config.getMultimedia()!=null) {
            config.getMultimedia().setActualRefreshRate(actualRefRate);
        }
    }

    @Override
    public void setDirectDown(boolean isDirectDown) {
        if(config.getCountdown()!=null) {
            config.getCountdown().setCountdownDown(isDirectDown);
        }
    }

    @Override
    public void setTimeToEvaluate(int seconds) {
        if(config.getCountdown()!=null) {
            config.getCountdown().setTimeToEvaluate(seconds);
        }
    }
}
