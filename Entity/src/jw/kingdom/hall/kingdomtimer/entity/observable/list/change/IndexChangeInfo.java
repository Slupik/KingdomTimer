package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

/**
 * All rights reserved & copyright ©
 */
public interface IndexChangeInfo<T> {
    T getChangedObject();
    int getLastIndex();
    int getCurrentIndex();
}
