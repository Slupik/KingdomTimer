package jw.kingdom.hall.kingdomtimer.config.json;

import com.google.gson.Gson;
import jw.kingdom.hall.kingdomtimer.config.Config;
import jw.kingdom.hall.kingdomtimer.config.ConfigStatic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static jw.kingdom.hall.kingdomtimer.config.ConfigStatic.BACKBONE;

/**
 * All rights reserved & copyright ©
 */
public class JsonConfig implements Config {
    private Config parent;
    private JsonConfigRoot config = new Gson().fromJson(BACKBONE, JsonConfigRoot.class);;

    @Override
    public void loadLocalData(String data) {
        config = new Gson().fromJson(data, JsonConfigRoot.class);
        if(null == parent) {
            config.applyParentConfig(ConfigStatic.DEFAULT);
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

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(this.toString());
        writer.close();
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
        setDefaultRefreshRate(source.getDefaultRefreshRate());

        setDirectDown(source.isDirectDown());
    }

    @Override
    public String toString(){
        return new Gson().toJson(config);
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
            return ConfigStatic.DEFAULT.isEnabledGleaming();
        }
    }

    @Override
    public boolean isEnabledShowMultimedia() {
        try {
            return config.getSpeaker().isShowMulti();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigStatic.DEFAULT.isEnabledShowMultimedia();
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
            return ConfigStatic.DEFAULT.isEnabledAutopilot();
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
            return ConfigStatic.DEFAULT.getMinRefreshRate();
        }
    }

    @Override
    public int getWarningRefreshRate() {
        try {
            return config.getMultimedia().getWarningRefreshRate();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigStatic.DEFAULT.getWarningRefreshRate();
        }
    }

    @Override
    public int getDefaultRefreshRate() {
        try {
            return config.getMultimedia().getDefaultRefreshRate();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfigStatic.DEFAULT.getDefaultRefreshRate();
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
    public void setDirectDown(boolean isDirectDown) {
        if(config.getCountdown()!=null) {
            config.getCountdown().setCountdownDown(isDirectDown);
        }
    }
}
