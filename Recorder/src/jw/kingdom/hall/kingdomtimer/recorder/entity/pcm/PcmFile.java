package jw.kingdom.hall.kingdomtimer.recorder.entity.pcm;

import java.io.*;
import java.nio.file.Files;

/**
 * All rights reserved & copyright ©
 */
public class PcmFile implements PcmData {

    private final File source;
    private final boolean improvePerformance;

    public PcmFile(File source) {
        this(source, true);
    }

    public PcmFile(File source, boolean improvePerformance) {
        this.source = source;
        this.improvePerformance = improvePerformance;
    }

    @Override
    public long getSize() {
        return source.length();
    }

    @Override
    public void appendTo(OutputStream output) {
        if(improvePerformance) {
            try {
                InputStream input = new FileInputStream(source);
                byte[] buf = new byte[1024*1024];
                int len;
                while((len=input.read(buf))>0){
                    output.write(buf,0,len);
                }
                output.close();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                output.write(getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public byte[] getBytes() throws IOException {
        return Files.readAllBytes(source.toPath());
    }
}
