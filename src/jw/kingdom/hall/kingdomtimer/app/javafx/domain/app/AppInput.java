package jw.kingdom.hall.kingdomtimer.app.javafx.domain.app;

import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;

/**
 * All rights reserved & copyright ©
 */
public interface AppInput {
    Config getConfig();
    RecordControl getRecorder();
}
