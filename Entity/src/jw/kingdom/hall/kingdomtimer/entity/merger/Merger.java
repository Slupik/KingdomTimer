package jw.kingdom.hall.kingdomtimer.entity.merger;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class Merger <T> {

    public abstract T merge(T target, T source);

    public List<T> mergeCommon(List<T> target, List<T> source) {
        List<T> merged = new ArrayList<>();
        for(T value:target) {
            for(T src:source) {
                if(isTheSameId(value, src)) {
                    merged.add(merge(value, src));
                }
            }
        }
        return merged;
    }

    protected abstract boolean isTheSameId(T v1, T v2);
}