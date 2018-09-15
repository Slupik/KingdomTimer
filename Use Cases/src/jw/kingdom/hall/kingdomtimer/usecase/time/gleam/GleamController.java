package jw.kingdom.hall.kingdomtimer.usecase.time.gleam;

/**
 * All rights reserved & copyright ©
 */
public interface GleamController {
    void enable();
    void disable();
    boolean isEnabled();

    void addGleammer(Gleammer gleammer);
    void removeGleammer(Gleammer gleammer);
}
