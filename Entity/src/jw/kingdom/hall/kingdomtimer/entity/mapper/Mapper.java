package jw.kingdom.hall.kingdomtimer.entity.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class Mapper <TS, TD> {

    public abstract TD map(TS value);

    public abstract TS reverseMap(TD value);

    public List<TD> map(List<TS> values) {
        List<TD> returnValues = new ArrayList<>(values.size());
        for (TS value : values) {
            returnValues.add(map(value));
        }
        return returnValues;
    }

    public List<TS> reverseMap(List<TD> values) {
        List<TS> returnValues = new ArrayList<>(values.size());
        for (TD value : values) {
            returnValues.add(reverseMap(value));
        }
        return returnValues;
    }
}