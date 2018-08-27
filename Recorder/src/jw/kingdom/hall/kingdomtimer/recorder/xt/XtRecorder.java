package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.*;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * All rights reserved & copyright ©
 */
public class XtRecorder implements Recorder {
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
        data.setPause(isPause);
    }

    @Override
    public void onStop() {
        recording.stop();
        backup.stop();
        saver.finalSave();
    }
}
