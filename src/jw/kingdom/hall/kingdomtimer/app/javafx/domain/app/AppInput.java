package jw.kingdom.hall.kingdomtimer.app.javafx.domain.app;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

/**
 * All rights reserved & copyright ©
 */
public interface AppInput {
    Config getConfig();
    Recorder getRecorder();
}
