/*
 * Created 05.09.18 16:37.
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.utils.observable;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<O extends Observable, T> {
    private final List<ChangeListener<O, T>> listeners = new ArrayList<>();

    public void addListener(ChangeListener<O, T> listener) {
        listeners.add(listener);
    }

    public void removeListener(ChangeListener<O, T> listener) {
        listeners.remove(listener);
    }

    protected void notifyAboutChange(O observedObject, T oldValue, T newValue) {
        for(ChangeListener<O, T> listener:listeners) {
            listener.onChange(observedObject, oldValue, newValue);
        }
    }
}
