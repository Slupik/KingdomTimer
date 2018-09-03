package jw.kingdom.hall.kingdomtimer.recorder.xt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class RawDataBuffer extends ByteArrayOutputStream {
    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public synchronized void write(int b) {
        super.write(b);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) {
        super.write(b, off, len);
    }
}
