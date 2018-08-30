package jw.kingdom.hall.kingdomtimer.data.record;

import jw.kingdom.hall.kingdomtimer.data.UniqueFileUtils;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.recorder.common.files.FileRecordCreator;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * All rights reserved & copyright ©
 */
public class DefaultFileRecordCreator implements FileRecordCreator {

    @Override
    public File getBackupFile(String extension) {
        createRootPath();
        return UniqueFileUtils.buildUniqueFile(getDestPath(), getBackupFileRawName(), extension);
    }

    /**
     * @return Name of file without extension
     */
    private String getBackupFileRawName() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        Date date = new Date();
        return "BACKUP_"+dateFormat.format(date);
    }

    @Override
    public File getFinalFile(String extension) {
        createRootPath();
        return UniqueFileUtils.buildUniqueFile(getDestPath(), getFinalFileRawName(), extension);
    }

    /**
     * @return Name of file without extension
     */
    private String getFinalFileRawName() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        return "Nagranie "+dateFormat.format(date);
    }

    private void createRootPath() {
        UniqueFileUtils.createPath(getDestPath());
    }

    private String getDestPath() {
        return AppConfig.getInstance().getRecordDestPath();
    }
}
