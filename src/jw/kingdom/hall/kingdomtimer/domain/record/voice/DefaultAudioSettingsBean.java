package jw.kingdom.hall.kingdomtimer.domain.record.voice;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.record.DefaultFileRecordCreator;
import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;
import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class DefaultAudioSettingsBean implements AudioSettingsBean {
    private FileRecordCreator fileRecordCreator;
    private boolean readDefaultMixSettings = false;
    private int rate = 44100;//ignore if(readDefaultMixSettings == true)
    private String sample = "INT24";//ignore if(readDefaultMixSettings == true)
    private int sampleSize = 3;//ignore if(readDefaultMixSettings == true) or if sample is set
    private int inputs = 1;
    private boolean selectDefaultDevice = true;
    private String deviceName = "";//ignore if(selectDefaultDevice == true)
    private String serviceSetupId = "CONSUMER_AUDIO";

    public DefaultAudioSettingsBean(Config config) {
        fileRecordCreator = new DefaultFileRecordCreator(config);
    }

    @Override
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public int getInputs() {
        return inputs;
    }

    public void setInputs(int inputs) {
        this.inputs = inputs;
    }

    @Override
    public int getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    @Override
    public boolean isToSelectDefaultDevice() {
        return selectDefaultDevice;
    }

    public void setToSelectDefaultDevice(boolean selectDefaultDevice) {
        this.selectDefaultDevice = selectDefaultDevice;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String getServiceSetupID() {
        return serviceSetupId;
    }

    public void setServiceSetupID(String serviceSetupId) {
        this.serviceSetupId = serviceSetupId;
    }

    @Override
    public FileRecordCreator getPaths() {
        return fileRecordCreator;
    }

    public void setPaths(FileRecordCreator fileRecordCreator) {
        this.fileRecordCreator = fileRecordCreator;
    }

    @Override
    public boolean isReadDefaultMixSettings() {
        return readDefaultMixSettings;
    }

    public void setReadDefaultMixSettings(boolean readDefaultMixSettings) {
        this.readDefaultMixSettings = readDefaultMixSettings;
    }
}
