package jw.kingdom.hall.kingdomtimer.recorder.entity.buffer;

import jw.kingdom.hall.kingdomtimer.recorder.entity.pcm.PcmData;

import java.io.OutputStream;

/**
 * All rights reserved & copyright ©
 */
public interface AudioDataBuffer extends PcmData {
    long getSize();
    void appendTo(OutputStream output);
    byte[] getBytes();

    void write(byte b[], int off, int len);
    void delete();
    boolean isNeedBackup();
}
