package jw.kingdom.hall.kingdomtimer.usecase.usecase;

/**
 * All rights reserved & copyright ©
 */
public interface InputBoundary<L extends OutputBoundary> {

    void addOutput(L output);
    void removeOutput(L output);
}
