package jw.kingdom.hall.kingdomtimer.domain.record.voice;

/**
 * All rights reserved & copyright ©
 */
public class VoiceRecorder {

    public void start(){

    }

    public void stop(){

    }

    public void pause(){

    }

    private static VoiceRecorder recorder;
    public static VoiceRecorder getInstance() {
        if(null == recorder) {
            recorder = new VoiceRecorder();
        }
        return recorder;
    }
    private VoiceRecorder(){}
}
