package jw.kingdom.hall.kingdomtimer.entity.observable.list;

/**
 * All rights reserved & copyright ©
 */
public interface ListListenerAddable<T> {
    void addListener(ListChangeListener<T> listener);
    void removeListener(ListChangeListener<T>listener);
}
