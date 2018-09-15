package jw.kingdom.hall.kingdomtimer.entity.observable.list;

import jw.kingdom.hall.kingdomtimer.entity.observable.list.change.ListChanges;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
@FunctionalInterface
public interface ListChangeListener<T> {
    void onChange(List<T> list, ListChanges<T> changes);
}
