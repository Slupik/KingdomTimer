package jw.kingdom.hall.kingdomtimer.data.file.save.log;

import jw.kingdom.hall.kingdomtimer.data.file.save.InstallationCatalogue;
import jw.kingdom.hall.kingdomtimer.log.LogFileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class DefaultLogFileProvider extends InstallationCatalogue implements LogFileProvider {

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
        return getFileAndCreate(getLogFileName()+".txt");
    }

    private String getLogFileName() {
        return "log_"+new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss.SSS").format(new Date());
    }

    @Override
    protected String getCatalogueName() {
        return LOG_CATALOGUE_NAME;
    }

}
