package jw.kingdom.hall.kingdomtimer.recorder.entity.pcm;

import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * All rights reserved & copyright ©
 */
public class PcmRam implements PcmData {

    private final AudioDataBuffer data;

    public PcmRam(AudioDataBuffer data){
        this.data = data;
    }

    @Override
    public long getSize() {
        return data.getBytes().length;
    }

    @Override
    public void appendTo(OutputStream output) {

    }

    @Override
    public byte[] getBytes() throws IOException {
        return data.getBytes();
    }
}
