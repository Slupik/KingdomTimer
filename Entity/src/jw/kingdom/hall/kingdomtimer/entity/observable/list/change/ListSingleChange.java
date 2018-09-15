package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

/**
 * All rights reserved & copyright ©
 */
public interface ListSingleChange<T> {
    boolean wasIndexChange();
    boolean wasCleared();
    boolean wasSet();
    boolean wasUpdated();
    boolean wasRemoved();
    boolean wasAdded();

    IndexChangeInfo<T> getIndexChange();
    SetChangeInfo<T> getSetChange();
    T getUpdate();
    T getRemoved();
    T getAdded();
}
