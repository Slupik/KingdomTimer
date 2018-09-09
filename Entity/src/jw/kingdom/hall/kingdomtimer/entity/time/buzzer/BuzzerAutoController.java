package jw.kingdom.hall.kingdomtimer.entity.time.buzzer;

import jw.kingdom.hall.kingdomtimer.entity.observable.field.ObservableField;

/**
 * All rights reserved & copyright ©
 */
public interface BuzzerAutoController {
    ObservableField<Boolean> isEnabledProperty();
    void setEnabled(Boolean isEnabled);
    boolean isEnabled();
}
