package jw.kingdom.hall.kingdomtimer.config.model;

import java.io.File;
import java.io.IOException;

/**
 * All rights reserved & copyright ©
 */
public interface Config extends ConfigReadable, ConfigEditable {
    void save(File file) throws IOException;
}
