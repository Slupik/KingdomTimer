package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

import java.util.*;

/**
 * All rights reserved & copyright ©
 */
public class ChangeFactory {

    public static <T> ListChanges<T> makeListChange(T removed, T set, int index){
        SetChangeInfo<T> info = new SimpleSetChangeInfo<>(removed, set, index);
        ListSingleChange<T> change = new SimpleListSingleChange<>(info);
        List<ListSingleChange<T>> list = Collections.singletonList(change);
        return new SimpleListChanges<>(new ArrayList<>(list));
    }

    public static <T> ListChanges<T> makeListChange(ChangeType type){
        if(type==ChangeType.CLEARING) {
            return makeListClear();
        } else {
            throw new FactoryException();
        }
    }

    public static <T> ListChanges<T> makeListClear(){
        ListSingleChange<T> change = new SimpleListSingleChange<>();
        List<ListSingleChange<T>> list = Collections.singletonList(change);
        return new SimpleListChanges<>(list);
    }

    public static <T> ListChanges<T> makeListChange(ChangeType type, T changed){
        return makeListChange(type, Collections.singletonList(changed));
    }

    public static <T> ListChanges<T> makeListChange(ChangeType type, Collection<? extends T> changed){
        return makeListChange(type, changed, -1);
    }

    public static <T> ListChanges<T> makeListChange(ChangeType type, T element, int index) {
        return makeListChange(type, Collections.singletonList(element), index);
    }

    public static <T> ListChanges<T> makeListChange(ChangeType type, Collection<? extends T> changed, int index) {
        List<ListSingleChange<T>> list = new ArrayList<>();
        for(T singleChange:changed) {
            list.add(new SimpleListSingleChange<>(type, singleChange));
        }
        return new SimpleListChanges<>(list, index);
    }
}
