package jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.file;

import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;

import java.io.*;
import java.nio.file.Files;

/**
 * All rights reserved & copyright ©
 */
public class FileBuffer implements AudioDataBuffer {

    private final File file;
    private OutputStream output;

    public FileBuffer(File file){
        this.file = file;
    }

    @Override
    public boolean isNeedBackup() {
        return false;
    }

    @Override
    public byte[] readAllBytes() {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public void write(byte[] b, int off, int len) {
        OutputStream output = getOutput();
        if(output!=null) {
            try {
                output.write(b, off, len);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("OutputStream is equal to 0. Cannot save data into the file.");
        }
    }

    @Override
    public void delete() {
        try {
            getOutput().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(file.exists()) {
            file.delete();
        }
    }

    private OutputStream getOutput() {
        if(output == null) {
            try {
                output = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return output;
    }
}
