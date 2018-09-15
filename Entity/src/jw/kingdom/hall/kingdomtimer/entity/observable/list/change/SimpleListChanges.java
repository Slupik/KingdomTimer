package jw.kingdom.hall.kingdomtimer.entity.observable.list.change;

import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class SimpleListChanges<T> implements ListChanges<T> {

    private final List<ListSingleChange<T>> changes;
    private final int startIndex;
    private int iterationIndex = 0;

    public SimpleListChanges(List<ListSingleChange<T>> changes){
        this(changes, -1);
    }

    public SimpleListChanges(List<ListSingleChange<T>> changes, int startIndex){
        this.changes = changes;
        this.startIndex = startIndex;
    }

    @Override
    public boolean hasNext() {
        return iterationIndex <changes.size();
    }

    @Override
    public ListSingleChange<T> next() {
        if(hasNext()) {
            ListSingleChange<T> change = changes.get(iterationIndex);
            iterationIndex++;
            return change;
        } else {
            return null;
        }
    }

    @Override
    public int getStartIndex() {
        return startIndex;
    }
}
