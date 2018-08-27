package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.xtaudio.xt.XtAudio;
import com.xtaudio.xt.XtDevice;
import com.xtaudio.xt.XtService;
import com.xtaudio.xt.XtSetup;

/**
 * All rights reserved & copyright ©
 */
class DeviceSelector {
    static XtDevice getDevice(){
        XtService service = XtAudio.getServiceBySetup(XtSetup.CONSUMER_AUDIO);
        return service.openDefaultDevice(false);
    }
}
