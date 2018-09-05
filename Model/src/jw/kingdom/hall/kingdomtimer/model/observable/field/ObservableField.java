/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.observable.field;

import jw.kingdom.hall.kingdomtimer.model.observable.Observable;

import java.util.ArrayList;
import java.util.List;

public class ObservableField<T> extends Observable<ObservableField<T>, T> {

    protected final List<FieldChangeListener<ObservableField<T>, T>> listeners = new ArrayList<>();
    protected T value;

    public ObservableField() {}

    public ObservableField(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        T oldValue = this.value;
        this.value = value;
        notifyAboutChange(this, oldValue, value);
    }

    public void addListener(FieldChangeListener<ObservableField<T>, T> listener) {
        listeners.add(listener);
    }

    public void removeListener(FieldChangeListener<ObservableField<T>, T> listener) {
        listeners.remove(listener);
    }

    protected void notifyAboutChange(ObservableField<T> observedObject, T oldValue, T newValue) {
        for(FieldChangeListener<ObservableField<T>, T> listener:listeners) {
            listener.onChange(observedObject, oldValue, newValue);
        }
    }
}
