package jw.kingdom.hall.kingdomtimer.usecase.usecase;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public abstract class UseCase<L extends OutputBoundary> implements InputBoundary<L> {

    protected final List<L> outputs = new ArrayList<>();

    @Override
    public void addOutput(L output) {
        outputs.add(output);
    }

    @Override
    public void removeOutput(L output) {
        outputs.remove(output);
    }
}
