package jw.kingdom.hall.kingdomtimer.domain.multimedia;

import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import org.jetbrains.annotations.NotNull;

/**
 * All rights reserved & copyright ©
 */
public interface MonitorPreviewController {
    void setRefreshInterval(int interval);

    boolean isWorking();
    boolean isPause();
    void setPause(boolean pause);

    void setMonitor(@NotNull Monitor monitor);
    Monitor getMonitor();

    void setScreenshotMaker(@NotNull MonitorScreenshotMaker maker);
    MonitorScreenshotMaker getScreenshotMaker();

    void addDisplay(@NotNull MonitorViewDisplay display);
    void removeDisplay(@NotNull MonitorViewDisplay display);
    void forceChangeDisplaysVisibility(boolean isShowing);
}
