package jw.kingdom.hall.kingdomtimer.recorder.xt;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import jw.kingdom.hall.kingdomtimer.recorder.utils.wav.WavDataSaver;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * All rights reserved & copyright ©
 */
class BufferDataSaver {
    private final ByteArrayOutputStream stream;
    private final int srate;
    private final int channel;
    private final int format;
    private final String destPath;

    BufferDataSaver(ByteArrayOutputStream stream, int srate, int channel, int format, String destPath) {
        this.stream = stream;
        this.srate = srate;
        this.channel = channel;
        this.format = format;
        this.destPath = destPath;
    }

    void finalSave() {
        File destWavFile = getDestFile(".wav");
        File destMp3File = getDestFile(".mp3");

        createRootPath();
        saveTo(destWavFile);
        try {
            //TODO problem with convert float32 wav to mp3, maybe library doesn't support this?
            convertToMp3(destWavFile, destMp3File);
            destWavFile.delete();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }

    void saveTo(File dest) {
        if(!dest.exists()) {
            try {
                dest.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            OutputStream destStream = new FileOutputStream(dest);
            byte[] data = stream.toByteArray();
            WavDataSaver.savePCM(destStream, data, srate, channel, format);
        } catch (IOException e) {
            e.printStackTrace();
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

    private File getDestFile(String extension) {
        String withoutExtension = getFilenameWithoutExtension();
        File file = new File(getRootPath() + withoutExtension+ extension);
        int index = 1;
        while (file.exists()) {
            file = new File(getRootPath() + withoutExtension + "[" + Integer.toString(index) + "]" + extension);
            index++;
        }
        return file;
    }

    private void createRootPath() {
        File rootDir = new File(getRootPath());
        rootDir.mkdirs();
    }

    private String getRootPath() {
        if(destPath==null || destPath.length()==0) {
            return "";
        } else {
            return destPath + File.separator;
        }
    }

    private String getFilenameWithoutExtension() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        return "Nagranie "+dateFormat.format(date);
    }
}
