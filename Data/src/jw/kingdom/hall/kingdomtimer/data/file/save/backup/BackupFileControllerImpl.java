package jw.kingdom.hall.kingdomtimer.data.file.save.backup;

import jw.kingdom.hall.kingdomtimer.data.file.save.InstallationCatalogue;
import jw.kingdom.hall.kingdomtimer.domain.file.BackupFileController;

import java.io.File;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BackupFileControllerImpl extends InstallationCatalogue implements BackupFileController {

    private final static String BACKUP_CATALOGUE_NAME = "backup";
    private final static String TIME_BACKUP_FILE = "time.json";

    @Override
    protected String getCatalogueName() {
        return BACKUP_CATALOGUE_NAME;
    }

    @Override
    public File getTimeFile() {
        return getFileAndCreate(TIME_BACKUP_FILE);
    }

    @Override
    public void deleteCatalogue() {
        deleteTaskPath();
    }
}
