package jw.kingdom.hall.kingdomtimer.recorder.common.files;

import java.io.File;

/**
 * All rights reserved & copyright ©
 */
public interface FileRecordCreator {
    File getBackupFile(String extension);
    File getFinalFile(String extension);
}
