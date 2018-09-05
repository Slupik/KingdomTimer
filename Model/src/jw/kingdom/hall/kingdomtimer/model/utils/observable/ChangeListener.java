/*
 * Created 05.09.18 16:37.
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.utils.observable;

public interface ChangeListener<O extends Observable, T> {
    void onChange(O observableObject, T oldValue, T newValue);
}
