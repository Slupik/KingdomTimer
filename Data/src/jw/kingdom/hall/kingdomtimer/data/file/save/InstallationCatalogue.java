package jw.kingdom.hall.kingdomtimer.data.file.save;

import jw.kingdom.hall.kingdomtimer.domain.utils.OSValidator;

import java.io.File;
import java.io.IOException;

/**
 * All rights reserved & copyright ©
 */
public abstract class InstallationCatalogue {

    private final static String MAIN_CATALOGUE_NAME = "KingdomHallTimer";

    protected File getFileAndCreate(String fullName) {
        File file = new File(getTaskPath()+File.separator+fullName);
        generate(file);
        return file;
    }

    private void generate(File file) {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getTaskPath(){
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

    protected void createRootPath() {
        UniqueFileUtils.createPath(getTaskPath());
    }

    protected void deleteTaskPath() {
        UniqueFileUtils.deletePath(getTaskPath());
    }

    private String getUserPath() {
        String path = System.getProperty("user.home");
        if(OSValidator.isWindows()) {
            path += File.separator + "AppData"+ File.separator +"Local";
        }
        return path + File.separator + MAIN_CATALOGUE_NAME + File.separator + getCatalogueName();
    }

    private String getLocalPath() {
        return System.getProperty("user.dir") + File.separator + getCatalogueName();
    }

    protected abstract String getCatalogueName();

}
