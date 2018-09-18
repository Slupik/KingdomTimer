package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.XtAudio;
import com.xtaudio.xt.XtDevice;
import com.xtaudio.xt.XtFormat;
import jw.kingdom.hall.kingdomtimer.recorder.RecordListener;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;
import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;
import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;
import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.file.FileBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class XtRecorder implements Recorder, Recording.Listener {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Semaphore threadBlocker = new Semaphore(0);

    private static XtFormat format;
    private final AudioSettingsBean settingsBean;
    private Recording recording;
    private AudioDataBuffer storage;
    private BufferDataSaver saver;
    private RecordBackup backup;

    public XtRecorder(AudioSettingsBean settingsBean){
        this.settingsBean = settingsBean;
        initXtPlatform(()->{
            try {
                XtDevice device = DeviceSelector.getDevice(settingsBean);
                format = ObjectsFactory.getFormat(settingsBean, device);

                recording = new Recording(device, format);
                recording.addListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadBlocker.release();
        });
        try {
            threadBlocker.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initXtPlatform(Runnable callback) {
        executor.execute(()-> {
            new XtAudio(null, null, null, null);
            callback.run();
        });
    }

    @Override
    public void onStart() {
        for(RecordListener listener: recordListeners) {
            listener.onStart();
        }
        storage = new FileBuffer(settingsBean.getPaths().getBackupFile(".pcm"));
        saver = ObjectsFactory.getSaver(storage, settingsBean, format);

        if(storage.isNeedBackup()) {
            backup = new RecordBackup(saver, settingsBean);
            backup.start(60);
        }

        executor.execute(()-> recording.start(storage));
    }

    @Override
    public void setPause(boolean isPause) {
        executor.execute(()-> recording.setPause(isPause));
    }

    @Override
    public void onStop() {
        for(RecordListener listener: recordListeners) {
            listener.onStop();
        }
        executor.execute(()->{
            if(recording!=null) recording.stop();
        });
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
