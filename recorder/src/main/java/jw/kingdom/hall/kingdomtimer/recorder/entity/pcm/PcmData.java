package jw.kingdom.hall.kingdomtimer.recorder.entity.pcm;

import java.io.IOException;
import java.io.OutputStream;

/**
 * All rights reserved & copyright ©
 */
public interface PcmData {
    /**
     * It is possible that return size is different than real.
     * It may happen if after return the size, a datao is added to the associated buffer
     * @return size of a buffer
     */
    long getSize();
    void appendTo(OutputStream output);
    byte[] getBytes() throws IOException;
}
