package jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.ram;

import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;

import java.io.*;
import java.nio.file.Files;

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
    public byte[] readAllBytes() {
        return buffer.toByteArray();
    }

    @Override
    public void write(byte[] b, int off, int len) {
        buffer.write(b, off, len);
    }

    @Override
    public void delete() {

    }
}
