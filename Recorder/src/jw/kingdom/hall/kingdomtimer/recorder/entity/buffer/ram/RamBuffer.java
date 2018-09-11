package jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.ram;

import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * All rights reserved & copyright ©
 */
public class RamBuffer implements AudioDataBuffer {

    private final ByteArrayOutputStream buffer = new RawDataBuffer();

    public RamBuffer(){

    }

    @Override
    public boolean isNeedBackup() {
        return true;
    }

    @Override
    public long getSize() {
        return buffer.size();
    }

    @Override
    public byte[] getBytes() {
        return buffer.toByteArray();
    }

    @Override
    public void appendTo(OutputStream output) {
        try {
            output.write(getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(byte[] b, int off, int len) {
        buffer.write(b, off, len);
    }

    @Override
    public void delete() {

    }
}
