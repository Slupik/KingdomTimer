package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.*;
import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;
import jw.kingdom.hall.kingdomtimer.recorder.entity.buffer.AudioDataBuffer;

import java.io.File;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class ObjectsFactory {
    static XtFormat getFormat(AudioSettingsBean bean, XtDevice device){
        XtMix mix;
        if(bean.isReadDefaultMixSettings()) {
            mix = device.getMix();
        } else {
            mix = new XtMix(bean.getRate(), getSampleById(bean.getSample()));
        }
        return new XtFormat(mix, bean.getInputs(), 0, 0, 0);
    }

    private static XtSample getSampleById(String id) {
        for(XtSample sample:XtSample.values()) {
            if(sample.toString().equalsIgnoreCase(id)) {
                return sample;
            }
        }
        return null;
    }

    static BufferDataSaver getSaver(AudioDataBuffer storage, AudioSettingsBean bean, XtFormat format){
        XtMix mix;
        if(bean.isReadDefaultMixSettings()) {
            mix = format.mix;
        } else {
            mix = new XtMix(bean.getRate(), getSampleById(bean.getSample()));
        }
        return new BufferDataSaver(storage, mix.rate, bean.getInputs(), XtAudio.getSampleAttributes(mix.sample).size*8, bean.getPaths());
    }

    static BufferDataSaver getSaver(RawDataBuffer data, AudioSettingsBean bean, XtFormat format){
        XtMix mix;
        if(bean.isReadDefaultMixSettings()) {
            mix = format.mix;
        } else {
            mix = new XtMix(bean.getRate(), getSampleById(bean.getSample()));
        }
        return new BufferDataSaver(data, mix.rate, bean.getInputs(), XtAudio.getSampleAttributes(mix.sample).size*8, bean.getPaths());
    }
}
