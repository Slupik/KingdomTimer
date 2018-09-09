package jw.kingdom.hall.kingdomtimer.entity.time.gleam;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;

/**
 * All rights reserved & copyright ©
 */
public interface GleamSwitcher {
    void setEnabled(boolean isEnabled);
    boolean isEnabled();
    ObservableField<Boolean> enabledProperty();
}
