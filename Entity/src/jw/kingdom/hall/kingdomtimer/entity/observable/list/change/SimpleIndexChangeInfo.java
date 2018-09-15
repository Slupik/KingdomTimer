package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

/**
 * All rights reserved & copyright ©
 */
public class SimpleIndexChangeInfo<T> implements IndexChangeInfo<T> {

    private final T object;
    private final int lastIndex;
    private final int currentIndex;

    public SimpleIndexChangeInfo(T object, int lastIndex, int currentIndex) {
        this.object = object;
        this.lastIndex = lastIndex;
        this.currentIndex = currentIndex;
    }

    @Override
    public T getChangedObject() {
        return object;
    }

    @Override
    public int getLastIndex() {
        return lastIndex;
    }

    @Override
    public int getCurrentIndex() {
        return currentIndex;
    }
}
