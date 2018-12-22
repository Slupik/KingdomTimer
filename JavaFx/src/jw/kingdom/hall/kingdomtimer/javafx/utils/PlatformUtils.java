package jw.kingdom.hall.kingdomtimer.javafx.utils;

import javafx.application.Platform;

/**
 * All rights reserved & copyright ©
 */
public class PlatformUtils {
    public static void runOnUiThread(Runnable runnable) {
        if(Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }

    private PlatformUtils(){}
}
