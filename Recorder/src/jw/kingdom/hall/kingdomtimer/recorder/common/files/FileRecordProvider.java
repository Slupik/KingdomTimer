package jw.kingdom.hall.kingdomtimer.recorder.common.files;

import java.io.File;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public interface FileRecordProvider {
    File getBackupFile(String extension);
    File getFinalFile(String extension);
}
