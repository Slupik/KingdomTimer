/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.utils.observable;

public class ObservableField<T> extends Observable<ObservableField<T>, T> {
    private T value;

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
}
