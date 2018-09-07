package jw.kingdom.hall.kingdomtimer.main.backup;

import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;
import jw.kingdom.hall.kingdomtimer.javafx.entity.bussines.BackupController;

/**
 * All rights reserved & copyright ©
 */
public class Backup implements BackupController {

    public Backup(){
        BackupManager.start();
    }

    @Override
    public void delete() {
        BackupManager.delete();
    }
}
