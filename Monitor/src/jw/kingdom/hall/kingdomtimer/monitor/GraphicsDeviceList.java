package jw.kingdom.hall.kingdomtimer.monitor;

import java.awt.*;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
//Alternative in the future: https://github.com/oshi/oshi
public interface GraphicsDeviceList {

    void addListener(Listener handler);
    void removeListener(Listener handler);

    List<GraphicsDevice> getDevices();

    interface Listener {
        void onPlugIn(GraphicsDevice device);
        void onPlugOut(GraphicsDevice device);
    }
}
