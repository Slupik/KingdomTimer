/*
 * Created 05.09.18 16:37.
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.entity.observable.field;

import jw.kingdom.hall.kingdomtimer.entity.observable.ChangeListener;
import jw.kingdom.hall.kingdomtimer.entity.observable.Observable;

public interface FieldChangeListener<O extends Observable, T> extends ChangeListener {
    void onChange(O observableObject, T oldValue, T newValue);
}
