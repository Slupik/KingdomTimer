package jw.kingdom.hall.kingdomtimer.recorder.xt;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordProvider;
import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;
import jw.kingdom.hall.kingdomtimer.recorder.utils.wav.WavDataSaver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class BufferDataSaver {
    private AudioDataBuffer storage;

    private final int bitrate;
    private final int srate;
    private final int channel;
    private final int format;
    private final FileRecordProvider paths;

    BufferDataSaver(AudioDataBuffer storage, int bitrate, int srate, int channel, int format, FileRecordProvider paths) {
        this.storage = storage;
        this.bitrate = bitrate;
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

    void saveBackupTo(File dest) {
        saveTo(dest);
    }

    private void saveTo(File dest) {
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
            WavDataSaver.savePCM(destStream, storage, srate, channel, format);
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

    private void convertToMp3(File source, File target) throws EncoderException {
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(bitrate);
        audio.setChannels(channel);
        audio.setSamplingRate(srate);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(source, target, attrs);
    }
}
