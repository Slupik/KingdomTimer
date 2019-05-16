package jw.kingdom.hall.kingdomtimer.config.model;

import java.io.File;
import java.io.IOException;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface Config extends ConfigReadable, ConfigEditable {
    void save(File file) throws IOException;
}
