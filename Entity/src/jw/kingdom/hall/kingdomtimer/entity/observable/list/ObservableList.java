package jw.kingdom.hall.kingdomtimer.entity.observable.list;


import jw.kingdom.hall.kingdomtimer.entity.observable.Observable;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.change.ChangeFactory;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.change.ChangeType;
import jw.kingdom.hall.kingdomtimer.entity.observable.list.change.ListChanges;

import java.util.*;

/**
 * All rights reserved & copyright ©
 */
public class ObservableList<T> extends Observable<ObservableList<T>, List<T>> implements List<T>, ListListenerAddable<T> {

    protected final List<ListChangeListener<T>> listeners = new ArrayList<>();
    protected final List<T> LIST = new ArrayList<>();

    @Override
    public void addListener(ListChangeListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ListChangeListener<T>listener) {
        listeners.remove(listener);
    }

    protected void notifyAboutChange(List<T> list, ListChanges<T> changes) {
        for(ListChangeListener<T> listener:listeners) {
            listener.onChange(list, changes);
        }
    }

    /*
            LIST IMPLEMENTATION BELOW
     */

    @Override
    public int size() {
        return LIST.size();
    }

    @Override
    public boolean isEmpty() {
        return LIST.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return LIST.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return LIST.iterator();
    }

    @Override
    public Object[] toArray() {
        return LIST.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return LIST.toArray(a);
    }

    @Override
    public boolean add(T t) {
        boolean status = LIST.add(t);
        if(status) {
            notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.ADDITION, t));
        }
        return status;
    }

    @Override
    public boolean remove(Object o) {
        boolean status = LIST.remove(o);
        if(status) {
            notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.DELETION, ((T) o)));
        }
        return status;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return LIST.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean status = LIST.addAll(c);
        if(status) {
            notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.ADDITION, c));
        }
        return status;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean status = LIST.addAll(index, c);
        if(status) {
            notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.ADDITION, c, index));
        }
        return status;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean status = LIST.removeAll(c);
        if(status) {
            notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.DELETION, getAsParsedList(c)));
        }
        return status;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        List<T> oldList = new ArrayList<>(LIST);
        List<T> removed = new ArrayList<>();
        List<T> added = new ArrayList<>();
        boolean status = LIST.retainAll(c);
        if(status) {
            for(T element:oldList) {
                if(!LIST.contains(element)) {
                    removed.add(element);
                }
            }
            for(T element:LIST) {
                if(!oldList.contains(element)) {
                    added.add(element);
                }
            }
            if(removed.size()>0) {
                notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.DELETION, removed));
            }
            if(added.size()>0) {
                notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.ADDITION, added));
            }
        }
        return LIST.retainAll(c);
    }

    private List<T> getAsParsedList(Collection<?> c) {
        List<T> parsed = new ArrayList<>();
        for(Object o:c) {
            parsed.add(((T) o));
        }
        return parsed;
    }

    @Override
    public void clear() {
        LIST.clear();
        notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.CLEARING));
    }

    @Override
    public T get(int index) {
        return LIST.get(index);
    }

    @Override
    public T set(int index, T element) {
        T lastItem = LIST.get(index);
        T value = LIST.set(index, element);
        notifyAboutChange(LIST, ChangeFactory.makeListChange(lastItem, element, index));
        return value;
    }

    @Override
    public void add(int index, T element) {
        LIST.add(index, element);
        notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.ADDITION, element, index));
    }

    @Override
    public T remove(int index) {
        T removed = LIST.remove(index);
        notifyAboutChange(LIST, ChangeFactory.makeListChange(ChangeType.DELETION, removed, index));
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        return LIST.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return LIST.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return LIST.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return LIST.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return LIST.subList(fromIndex, toIndex);
    }
}
