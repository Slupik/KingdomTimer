package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.*;

import java.io.OutputStream;

import static jw.kingdom.hall.kingdomtimer.recorder.utils.ByteUtils.convertToByteArray;

/**
 * All rights reserved & copyright ©
 */
class Recording {
    private final XtDevice device;
    private final XtFormat format;
    private final int sampleSize;
    private XtStream stream;

    Recording(XtDevice device, XtFormat format, int sampleSize) {
        this.device = device;
        this.format = format;
        this.sampleSize = sampleSize;
    }

    void start(OutputStream output){
        init(output);
        stream.start();
    }

    private void init(OutputStream output) {
        XtBuffer buffer = device.getBuffer(format);
        stream = device.openStream(format, true, false, buffer.current, new XtStreamCallback() {
            @Override
            public void callback(XtStream stream, Object input, Object output, int frames, double time,
                                 long position, boolean timeValid, long error, Object user) throws Exception {
                byte[] array = convertToByteArray(input);
                if (frames > 0) {
                    ((OutputStream) user).write(array, 0, frames * sampleSize);
                }

            }
        }, (i, o) -> {
        }, output);
    }

    void stop(){
        stream.stop();
        device.close();
    }
}
