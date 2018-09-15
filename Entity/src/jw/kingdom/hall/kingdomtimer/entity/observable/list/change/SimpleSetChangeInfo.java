package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

/**
 * All rights reserved & copyright ©
 */
public class SimpleSetChangeInfo<T> implements SetChangeInfo<T> {

    private final T removed;
    private final T set;
    private final int index;

    public SimpleSetChangeInfo(T removed, T set, int index) {
        this.removed = removed;
        this.set = set;
        this.index = index;
    }

    @Override
    public T getRemoved() {
        return removed;
    }

    @Override
    public T getSet() {
        return set;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
