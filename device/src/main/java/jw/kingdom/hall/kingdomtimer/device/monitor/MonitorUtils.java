package jw.kingdom.hall.kingdomtimer.device.monitor;

import java.awt.*;

import static java.awt.GraphicsDevice.TYPE_RASTER_SCREEN;

/**
 * All rights reserved & copyright ©
 */
abstract class MonitorUtils {

    static boolean listContains(GraphicsDevice[] list, GraphicsDevice gd) {
        for (GraphicsDevice checkingGD : list) {
            if (gd.getType() == TYPE_RASTER_SCREEN && checkingGD.getIDstring().equals(gd.getIDstring())) {
                return true;
            }
        }
        return false;
    }

    private MonitorUtils(){}
}
