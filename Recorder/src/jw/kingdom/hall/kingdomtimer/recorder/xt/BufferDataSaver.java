package jw.kingdom.hall.kingdomtimer.recorder.xt;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;
import jw.kingdom.hall.kingdomtimer.recorder.utils.wav.WavDataSaver;

import java.io.*;
import java.nio.file.Files;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class BufferDataSaver {
    private ByteArrayOutputStream stream;
    private File storage;

    private final int srate;
    private final int channel;
    private final int format;
    private final FileRecordCreator paths;

    BufferDataSaver(ByteArrayOutputStream stream, int srate, int channel, int format, FileRecordCreator paths) {
        this.stream = stream;
        this.srate = srate;
        this.channel = channel;
        this.format = format;
        this.paths = paths;
    }

    BufferDataSaver(File storage, int srate, int channel, int format, FileRecordCreator paths) {
        this.storage = storage;
        this.srate = srate;
        this.channel = channel;
        this.format = format;
        this.paths = paths;
    }

    void finalSave(Runnable callbackOnEnd) {
        new Thread(()->{
            File destWavFile = getDestFile(".wav");
            File destMp3File = getDestFile(".mp3");

            saveTo(destWavFile);
            try {
                //TODO problem with convert float32 wav to mp3, maybe library doesn't support this?
                convertToMp3(destWavFile, destMp3File);
                destWavFile.delete();
            } catch (EncoderException e) {
                e.printStackTrace();
            }
            callbackOnEnd.run();
        }).start();
    }

    private File getDestFile(String extension) {
        return paths.getFinalFile(extension);
    }

    void saveTo(File dest) {
        if(!dest.exists()) {
            try {
                dest.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStream destStream = null;
        try {
            destStream = new FileOutputStream(dest);
            byte[] data = getBytesToConvert();
            WavDataSaver.savePCM(destStream, data, srate, channel, format);
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
        System.gc();
    }

    private byte[] getBytesToConvert() {
        if(stream!=null) {
            return stream.toByteArray();
        } else {
            try {
                return Files.readAllBytes(storage.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
    }

    private void convertToMp3(File source, File target) throws EncoderException {
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(channel));
        audio.setSamplingRate(new Integer(srate));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(source, target, attrs);
    }
}
