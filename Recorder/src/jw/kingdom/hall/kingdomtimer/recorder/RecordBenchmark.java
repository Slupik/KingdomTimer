package jw.kingdom.hall.kingdomtimer.recorder;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;
import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.file.FileBuffer;
import jw.kingdom.hall.kingdomtimer.recorder.utils.wav.WavDataSaver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * All rights reserved & copyright ©
 */
public class RecordBenchmark {
    //0 - SSD
    //1 - HDD
    //2 - pendrive
    private final static short TEST_TYPE = 0;
    private final static boolean PRINT_PARTIAL_DATA = true;

    private final static File rawFile = getRawFile();
    private final static File dest = getWavFile();
    private final static File destMp = getMpFile();

    public static void main(String[] args) throws InterruptedException {
        checkTimeOfAction(()-> createWAV(rawFile, dest), 10);
        checkTimeOfAction(()-> convertToMp3(dest, destMp), 2);
        System.gc();
    }

    private synchronized static void checkTimeOfAction(Runnable runnable, int repeats) {
        long[] scores = new long[repeats];
        for(int i=0;i<scores.length;i++) {
            long start = System.currentTimeMillis();
            runnable.run();
            long end = System.currentTimeMillis();
            long time = end-start;
            scores[i] = time;
        }
        int totalTime = 0;
        for(long score:scores) {
            totalTime+=score;
            if(PRINT_PARTIAL_DATA) {
                System.out.println("time = " + score);
            }
        }
        System.out.println("avg = " + (totalTime/scores.length));
    }

    private static void createWAV(File source, File dest){
        OutputStream destStream = null;
        try {
            destStream = new FileOutputStream(dest);
//            byte[] data = getBytesToConvert(rawFile);
            AudioDataBuffer data = new FileBuffer(source);
            WavDataSaver.savePCM(destStream, data, 44100, 1, 24);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (destStream != null) {
            try {
                destStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void convertToMp3(File source, File target) {
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(128000);
        audio.setChannels(1);
        audio.setSamplingRate(44100);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        try {
            encoder.encode(source, target, attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }

    private static File getRawFile() {
        switch (TEST_TYPE) {
            case 0:
                return new File("bigraw.pcm");
            case 1:
                return new File("H:\\benchmark\\bigraw.pcm");
            case 2:
                return new File("E:\\benchmark\\bigraw.pcm");
            default:
                return null;
        }
    }

    private static File getWavFile() {
        switch (TEST_TYPE) {
            case 0:
                return new File("bigraw.wav");
            case 1:
                return new File("H:\\benchmark\\bigraw.wav");
            case 2:
                return new File("E:\\benchmark\\bigraw.wav");
            default:
                return null;
        }
    }

    private static File getMpFile() {
        switch (TEST_TYPE) {
            case 0:
                return new File("bigraw.mp3");
            case 1:
                return new File("H:\\benchmark\\bigraw.mp3");
            case 2:
                return new File("E:\\benchmark\\bigraw.mp3");
            default:
                return null;
        }
    }
}
