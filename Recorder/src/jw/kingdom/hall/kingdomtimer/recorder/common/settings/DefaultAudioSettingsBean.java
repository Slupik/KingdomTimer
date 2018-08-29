package jw.kingdom.hall.kingdomtimer.recorder.common.settings;

/**
 * All rights reserved & copyright ©
 */
public class DefaultAudioSettingsBean implements AudioSettingsBean {
    private String destinationFolder = "";
    private boolean readDefaultMixSettings = false;
    private int rate = 44100;//ignore if(readDefaultMixSettings == true)
    private String sample = "INT24";//ignore if(readDefaultMixSettings == true)
    private int sampleSize = 3;//ignore if(readDefaultMixSettings == true) or if sample is set
    private int inputs = 1;
    private boolean selectDefaultDevice = true;
    private String deviceName = "";//ignore if(selectDefaultDevice == true)
    private String serviceSetupId = "CONSUMER_AUDIO";

    @Override
    public String getDestinationFolder() {
        return destinationFolder;
    }

    public void setDestinationFolder(String destinationFolder) {
        this.destinationFolder = destinationFolder;
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
    public boolean isReadDefaultMixSettings() {
        return readDefaultMixSettings;
    }

    public void setReadDefaultMixSettings(boolean readDefaultMixSettings) {
        this.readDefaultMixSettings = readDefaultMixSettings;
    }
}
