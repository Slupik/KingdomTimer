package jw.kingdom.hall.kingdomtimer.domain.backup;

import jw.kingdom.hall.kingdomtimer.data.UniqueFileUtils;
import jw.kingdom.hall.kingdomtimer.domain.utils.OSValidator;

import java.io.File;
import java.io.IOException;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
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

    static void deleteRootPath() {
        UniqueFileUtils.deletePath(getRootPath());
    }

    private static String getRootPath(){
        File file = new File(getLocalPath());
        if(!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception ignore) {}
        }
        if(file.canWrite()) {
            return file.getAbsolutePath();
        } else {
            return getUserPath();
        }
    }

    private static String getUserPath() {
        String path = System.getProperty("user.home");
        if(OSValidator.isWindows()) {
            path += File.separator + "AppData"+ File.separator +"Local";
        }
        return path + File.separator + MAIN_CATALOGUE_NAME + File.separator + BACKUP_CATALOGUE_NAME;
    }

    private static String getLocalPath() {
        return System.getProperty("user.dir") + File.separator + BACKUP_CATALOGUE_NAME;
    }

}
