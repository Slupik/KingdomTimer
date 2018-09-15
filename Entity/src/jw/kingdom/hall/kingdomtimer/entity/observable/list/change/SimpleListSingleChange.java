package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

/**
 * All rights reserved & copyright ©
 */
public class SimpleListSingleChange<T> implements ListSingleChange<T> {

    private final ChangeType type;
    private final IndexChangeInfo<T> indexChange;
    private final T changed;
    private final SetChangeInfo<T> setChange;

    public SimpleListSingleChange() {
        this(ChangeType.CLEARING, null);
    }

    public SimpleListSingleChange(ChangeType type, T changed) {
        this.setChange = null;
        this.type = type;
        this.indexChange = null;
        this.changed = changed;
    }

    public SimpleListSingleChange(IndexChangeInfo<T> indexChange) {
        this.setChange = null;
        this.type = ChangeType.INDEX;
        this.indexChange = indexChange;
        this.changed = null;
    }

    public SimpleListSingleChange(SetChangeInfo<T> setChange) {
        this.setChange = setChange;
        this.type = ChangeType.SET;
        this.indexChange = null;
        this.changed = null;
    }

    @Override
    public boolean wasIndexChange() {
        return type==ChangeType.INDEX;
    }

    @Override
    public boolean wasCleared() {
        return type==ChangeType.CLEARING;
    }

    @Override
    public boolean wasSet() {
        return type==ChangeType.SET;
    }

    @Override
    public boolean wasUpdated() {
        return type==ChangeType.UPDATE;
    }

    @Override
    public boolean wasRemoved() {
        return type==ChangeType.DELETION;
    }

    @Override
    public boolean wasAdded() {
        return type==ChangeType.ADDITION;
    }

    @Override
    public IndexChangeInfo<T> getIndexChange() {
        if(wasIndexChange()) {
            return indexChange;
        }
        return null;
    }

    @Override
    public SetChangeInfo<T> getSetChange() {
        return null;
    }

    @Override
    public T getUpdate() {
        return getChange(ChangeType.UPDATE);
    }

    @Override
    public T getRemoved() {
        return getChange(ChangeType.DELETION);
    }

    @Override
    public T getAdded() {
        return getChange(ChangeType.ADDITION);
    }

    private T getChange(ChangeType askType) {
        if(type==askType) {
            return changed;
        }
        return null;
    }
}
