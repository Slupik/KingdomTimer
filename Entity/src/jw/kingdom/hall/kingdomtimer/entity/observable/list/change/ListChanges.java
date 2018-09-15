package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

/**
 * All rights reserved & copyright ©
 */
public interface ListChanges<T> {
    boolean hasNext();
    ListSingleChange<T> next();
    int getStartIndex();
}
