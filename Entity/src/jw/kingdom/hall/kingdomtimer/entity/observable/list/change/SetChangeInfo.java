package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

/**
 * All rights reserved & copyright ©
 */
public interface SetChangeInfo<T> {
    T getRemoved();
    T getSet();
    int getIndex();
}
