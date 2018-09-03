package jw.kingdom.hall.kingdomtimer.domain.backup;

import jw.kingdom.hall.kingdomtimer.data.UniqueFileUtils;
import jw.kingdom.hall.kingdomtimer.domain.utils.OSValidator;

import java.io.File;
import java.io.IOException;

/**
 * All rights reserved & copyright ©
 */
//TODO merge with ConfigFiles
abstract class FileManager {

    private final static String MAIN_CATALOGUE_NAME = "KingdomHallTimer";
    private final static String BACKUP_CATALOGUE_NAME = "backup";

    private final static String TIME_BACKUP_FILE = "time.json";

    static File getScheduleFile() {
        createRootPath();
        File file = new File(getRootPath()+File.separator+TIME_BACKUP_FILE);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private static void createRootPath() {
        UniqueFileUtils.createPath(getRootPath());
    }

    private static String getRootPath(){
        File file = new File(getLocalPath());
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ignore) {}
        }
        if(file.canWrite()) {
            return file.getAbsolutePath();
        } else {
            return getUserPath();
        }
    }

    private static String getUserPath() {
        if(OSValidator.isWindows()) {
            return addCatalogue(File.separator + "AppData"+ File.separator +"Local");
        } else {
            return addCatalogue(System.getProperty("user.home"));
        }
    }

    private static String getLocalPath() {
        return addCatalogue(System.getProperty("user.dir"));
    }

    private static String addCatalogue(String root) {
        return root + File.separator + MAIN_CATALOGUE_NAME + File.separator + BACKUP_CATALOGUE_NAME;
    }
}
