package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.*;
import jw.kingdom.hall.kingdomtimer.recorder.RecordListener;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;
import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;
import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.file.FileBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
//TODO get all native recording into one thread using ex. ExecutorService executor = Executors.newSingleThreadExecutor();
public class XtRecorder implements Recorder, Recording.Listener {
    private static XtFormat format;
    private final AudioSettingsBean settingsBean;
    private Recording recording;
    private AudioDataBuffer storage;
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
        for(RecordListener listener: recordListeners) {
            listener.onStart();
        }
        storage = new FileBuffer(settingsBean.getPaths().getBackupFile(".pcm"));
        saver = ObjectsFactory.getSaver(storage, settingsBean, format);

        recording.start(storage);
        if(storage.isNeedBackup()) {
            backup = new RecordBackup(saver, settingsBean);
            backup.start(60);
        }
    }

    @Override
    public void setPause(boolean isPause) {
        recording.setPause(isPause);
    }

    @Override
    public void onStop() {
        for(RecordListener listener: recordListeners) {
            listener.onStop();
        }
        if(recording!=null) recording.stop();
        if(backup!=null) backup.stop();
        AudioDataBuffer storageCopy = storage;
        if(saver!=null) saver.finalSave(() -> new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(storageCopy!=null) {
                storageCopy.delete();
            }
            for(RecordListener listener: recordListeners) {
                listener.onEnd();
            }
        }).start());
        setTotalFrames(0);
    }

    private List<RecordListener> recordListeners = new ArrayList<>();
    @Override
    public void addListener(RecordListener recordListener) {
        recordListeners.add(recordListener);
    }
    @Override
    public void removeListener(RecordListener recordListener) {
        recordListeners.remove(recordListener);
    }

    private long totalFrames = 0;
    @Override
    public void onNewFrames(int framesCount) {
        setTotalFrames(totalFrames+framesCount);
    }
    private void setTotalFrames(long framesCount) {
        totalFrames = framesCount;
        int time = (int) (totalFrames/format.mix.rate);
        for(RecordListener listener: recordListeners) {
            listener.onNewTime(time);
        }
    }
}
