package jw.kingdom.hall.kingdomtimer.usecase.time.buzzer;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;

/**
 * All rights reserved & copyright ©
 */
public interface BuzzerAutoController {
    ObservableField<Boolean> isEnabledProperty();
    void setEnabled(Boolean isEnabled);
    boolean isEnabled();

    void addListener(BuzzerListener listener);
    void removeListener(BuzzerListener listener);
}
