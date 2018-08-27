package jw.kingdom.hall.kingdomtimer.recorder.xt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * All rights reserved & copyright ©
 */
class RawDataBuffer extends ByteArrayOutputStream {
    private boolean pause;

    void setPause(boolean isPause) {
        pause = isPause;
    }

    @Override
    public void write(byte[] b) throws IOException {
        if(!pause) {
            super.write(b);
        }
    }

    @Override
    public synchronized void write(int b) {
        if(!pause) {
            super.write(b);
        }
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) {
        if(!pause) {
            super.write(b, off, len);
        }
    }
}
