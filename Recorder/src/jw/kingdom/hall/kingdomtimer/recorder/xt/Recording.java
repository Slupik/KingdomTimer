package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.*;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static jw.kingdom.hall.kingdomtimer.recorder.utils.ByteUtils.convertToByteArray;

/**
 * All rights reserved & copyright ©
 */
class Recording {
    private final XtDevice device;
    private final XtFormat format;
    private final int sampleSize;
    private XtStream stream;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private boolean pause;

    Recording(XtDevice device, XtFormat format) {
        this.device = device;
        this.format = format;
        this.sampleSize = XtAudio.getSampleAttributes(format.mix.sample).size;
    }

    void start(OutputStream output){
        init(output);
        stream.start();
    }

    @SuppressWarnings("Convert2Lambda")
    private void init(OutputStream output) {
        XtBuffer buffer = device.getBuffer(format);
        stream = device.openStream(format, true, false, buffer.current, new XtStreamCallback() {
            @Override
            public void callback(XtStream stream, Object input, Object output, int frames, double time,
                                 long position, boolean timeValid, long error, Object user) throws Exception {
                byte[] array = convertToByteArray(input);
                if (frames > 0) {
                    if(!pause) {
                        ((OutputStream) user).write(array, 0, frames * sampleSize);
                        notifyAboutNewFrames(frames);
                    }
                }

            }
        }, (i, o) -> {
        }, output);
    }

    void stop(){
        stream.stop();
        stream = null;
        //After close device recording again from the same object is impossible
//        device.close();
    }

    void setPause(boolean isPause) {
        pause = isPause;
    }



    private void notifyAboutNewFrames(int framesCount) {
        if(pause) {
            return;
        }
        executor.execute(() -> {
            for(Listener listener:listeners) {
                listener.onNewFrames(framesCount);
            }
        });
    }
    private List<Listener> listeners = new ArrayList<>();
    public void addListener(Listener listener){
        listeners.add(listener);
    }
    public void removeListener(Listener listener){
        listeners.remove(listener);
    }
    public interface Listener {
        void onNewFrames(int framesCount);
    }
}
