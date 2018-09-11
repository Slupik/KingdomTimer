package jw.kingdom.hall.kingdomtimer.recorder.entity.buffer;

/**
 * All rights reserved & copyright ©
 */
public interface AudioDataBuffer {
    byte[] readAllBytes();
    void write(byte b[], int off, int len);
    void delete();
    boolean isNeedBackup();
}
