/*
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.model.observable.list;

import jw.kingdom.hall.kingdomtimer.model.observable.Observable;

import java.util.*;

public class ObservableArray<T> extends Observable<ObservableArray<T>, List<T>> implements List<T> {

    protected final List<ListChangeListener<ObservableArray<T>, List<T>>> listeners = new ArrayList<>();
    protected final List<T> LIST = new ArrayList<>();

    public void addListener(ListChangeListener<ObservableArray<T>, List<T>> listener) {
        listeners.add(listener);
    }

    public void removeListener(ListChangeListener<ObservableArray<T>, List<T>> listener) {
        listeners.remove(listener);
    }

    protected void notifyAboutChange(ObservableArray<T> observedObject, List<T> list) {
        for(ListChangeListener<ObservableArray<T>, List<T>> listener:listeners) {
            listener.onChange(observedObject, list);
        }
    }

    /*
            BOILERPLATE CODE BELOW
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
            notifyAboutChange(this, LIST);
        }
        return status;
    }

    @Override
    public boolean remove(Object o) {
        boolean status = LIST.remove(o);
        if(status) {
            notifyAboutChange(this, LIST);
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
            notifyAboutChange(this, LIST);
        }
        return status;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean status = LIST.addAll(c);
        if(status) {
            notifyAboutChange(this, LIST);
        }
        return status;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean status = LIST.removeAll(c);
        if(status) {
            notifyAboutChange(this, LIST);
        }
        return status;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean status = LIST.retainAll(c);
        if(status) {
            notifyAboutChange(this, LIST);
        }
        return LIST.retainAll(c);
    }

    @Override
    public void clear() {
        LIST.clear();
        notifyAboutChange(this, LIST);
    }

    @Override
    public T get(int index) {
        return LIST.get(index);
    }

    @Override
    public T set(int index, T element) {
        T lastItem = LIST.set(index, element);
        notifyAboutChange(this, LIST);
        return lastItem;
    }

    @Override
    public void add(int index, T element) {
        LIST.add(index, element);
        notifyAboutChange(this, LIST);
    }

    @Override
    public T remove(int index) {
        T removed = LIST.remove(index);
        notifyAboutChange(this, LIST);
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
