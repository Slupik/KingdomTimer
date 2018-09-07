package jw.kingdom.hall.kingdomtimer.data.log;

import jw.kingdom.hall.kingdomtimer.data.UniqueFileUtils;
import jw.kingdom.hall.kingdomtimer.entity.utils.OSValidator;
import jw.kingdom.hall.kingdomtimer.log.LogFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
//TODO merge with other files managers
public class DefaultLogFile implements LogFile {

    private final static String MAIN_CATALOGUE_NAME = "KingdomHallTimer";
    private final static String LOG_CATALOGUE_NAME = "logs";

    private File dest = null;

    @Override
    public File getDest() {
        createRootPath();
        if(dest==null) {
            dest = generateFile();
        }
        return dest;
    }

    private File generateFile() {
        File file = new File(getRootPath()+File.separator+getLogFileName()+".txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private String getLogFileName() {
        return "log_"+new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss.SSS").format(new Date());
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
        return path + File.separator + MAIN_CATALOGUE_NAME + File.separator + LOG_CATALOGUE_NAME;
    }

    private static String getLocalPath() {
        return System.getProperty("user.dir") + File.separator + LOG_CATALOGUE_NAME;
    }

}
