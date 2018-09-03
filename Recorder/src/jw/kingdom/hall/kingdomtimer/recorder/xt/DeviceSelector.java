package jw.kingdom.hall.kingdomtimer.recorder.xt;

import com.sun.istack.internal.Nullable;
import com.xtaudio.xt.XtAudio;
import com.xtaudio.xt.XtDevice;
import com.xtaudio.xt.XtService;
import com.xtaudio.xt.XtSetup;
import jw.kingdom.hall.kingdomtimer.recorder.common.settings.AudioSettingsBean;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class DeviceSelector {

    @Nullable
    static XtDevice getDevice(AudioSettingsBean settingsBean){
        XtSetup setup = getSetupById(settingsBean.getServiceSetupID());
        XtService service = XtAudio.getServiceBySetup(setup);
        if(settingsBean.isToSelectDefaultDevice()) {
            return service.openDefaultDevice(false);
        } else {
            for(int i=0;i<service.getDeviceCount();i++) {
                XtDevice device = service.openDevice(i);
                if(device.getName().equals(settingsBean.getDeviceName())) {
                    return device;
                } else {
                    device.close();
                }
            }
        }
        return null;
    }

    @Nullable
    private static XtSetup getSetupById(String id) {
        for(XtSetup setup:XtSetup.values()) {
            if(setup.name().equalsIgnoreCase(id)) {
                return setup;
            }
        }
        return null;
    }
}
