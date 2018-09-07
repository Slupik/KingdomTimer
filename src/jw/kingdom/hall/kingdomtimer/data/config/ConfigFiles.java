package jw.kingdom.hall.kingdomtimer.data.config;

import jw.kingdom.hall.kingdomtimer.model.utils.OSValidator;

import java.io.File;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
/*
Class doesn't support macOS
 */
abstract class ConfigFiles {
    private final static String CATALOGUE_NAME = "KingdomHallTimer";
    private final static String DEFAULT_NAME = "config.json";

    private ConfigFiles(){}

    static File getLocal(){
        String path = System.getProperty("user.dir");
        return new File(addFilePath(path));
    }

    static File getUser(){
        String path = System.getProperty("user.home");
        if(OSValidator.isWindows()) {
            path += File.separator + "AppData"+ File.separator +"Local";
        }
        return new File(addFilePath(addCatalogue(path)));
    }

    static File getGlobal(){
        String path = System.getenv("ProgramFiles");
        if(path==null) {
            path = "/usr/share";
        }
        return new File(addFilePath(addCatalogue(path)));
    }

    private static String addCatalogue(String root) {
        return root + File.separator + CATALOGUE_NAME;
    }

    private static String addFilePath(String root) {
        return root + File.separator + DEFAULT_NAME;
    }
}
