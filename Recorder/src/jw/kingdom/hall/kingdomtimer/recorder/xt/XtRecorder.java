package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.*;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
//TODO get all native recording into one thread using ex. ExecutorService executor = Executors.newSingleThreadExecutor();
public class XtRecorder implements Recorder, Recording.Listener {
    private static XtFormat format;
    private final AudioSettingsBean settingsBean;
    private Recording recording;
    private RawDataBuffer data = new RawDataBuffer();
    private BufferDataSaver saver;
    private RecordBackup backup;

    public XtRecorder(AudioSettingsBean settingsBean){
        this.settingsBean = settingsBean;
        try {
            initXtPlatform();
            XtDevice device = DeviceSelector.getDevice(settingsBean);
            format = ObjectsFactory.getFormat(settingsBean, device);

            recording = new Recording(device, format);
            recording.addListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initXtPlatform() {
        XtAudio audio = new XtAudio(null, null, null, null);
    }

    @Override
    public void onStart() {
        data = new RawDataBuffer();
        saver = ObjectsFactory.getSaver(data, settingsBean, format);
        backup = new RecordBackup(saver, settingsBean);

        recording.start(data);
        backup.start(60);
    }

    @Override
    public void setPause(boolean isPause) {
        recording.setPause(isPause);
    }

    @Override
    public void onStop() {
        if(recording!=null) recording.stop();
        if(backup!=null) backup.stop();
        if(saver!=null) saver.finalSave();
        setTotalFrames(0);
    }

    private List<Listener> listeners = new ArrayList<>();
    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private long totalFrames = 0;
    @Override
    public void onNewFrames(int framesCount) {
        setTotalFrames(totalFrames+framesCount);
    }
    private void setTotalFrames(long framesCount) {
        totalFrames = framesCount;
        int time = (int) (totalFrames/format.mix.rate);
        for(Listener listener:listeners) {
            listener.onNewTime(time);
        }
    }
}
