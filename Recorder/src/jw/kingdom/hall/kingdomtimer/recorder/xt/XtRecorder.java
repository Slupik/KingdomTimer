package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.*;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class XtRecorder implements Recorder, Recording.Listener {
    private static final int SAMPLE_SIZE = 3;
    private static final XtFormat FORMAT = new XtFormat(new XtMix(44100, XtSample.INT24), 1, 0, 0, 0);

    private Recording recording;
    private RawDataBuffer data = new RawDataBuffer();
    private BufferDataSaver saver = new BufferDataSaver(data, 44100, 1, 24);
    private RecordBackup backup = new RecordBackup(saver);

    public XtRecorder(){
        try {
            initXtPlatform();
            XtDevice device = DeviceSelector.getDevice();
            recording = new Recording(device, FORMAT, SAMPLE_SIZE);
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
        data.reset();
        recording.start(data);
        backup.start(60);
    }

    @Override
    public void setPause(boolean isPause) {
        recording.setPause(isPause);
    }

    @Override
    public void onStop() {
        recording.stop();
        backup.stop();
        saver.finalSave();
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
        int time = (int) (totalFrames/FORMAT.mix.rate);
        for(Listener listener:listeners) {
            listener.onNewTime(time);
        }
    }
}
