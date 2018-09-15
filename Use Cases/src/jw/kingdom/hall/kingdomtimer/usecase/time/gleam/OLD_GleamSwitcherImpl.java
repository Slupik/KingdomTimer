package jw.kingdom.hall.kingdomtimer.usecase.time.gleam;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;
import jw.kingdom.hall.kingdomtimer.entity.time.gleam.GleamSwitcher;

/**
 * All rights reserved & copyright ©
 */
public class OLD_GleamSwitcherImpl implements GleamSwitcher {
    private final ObservableField<Boolean> enabled = new ObservableField<>(true);

    @Override
    public void setEnabled(boolean isEnabled) {
        enabled.setValue(isEnabled);
    }

    @Override
    public boolean isEnabled() {
        return enabled.getValue();
    }

    @Override
    public ObservableField<Boolean> enabledProperty() {
        return enabled;
    }
}
