package jw.kingdom.hall.kingdomtimer.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class LogSaver {
    private static final String ENTER = System.getProperty("line.separator");
    private final LogFileProvider FILE;

    LogSaver(LogFileProvider file) {
        FILE = file;
    }

    void printHeader() {
        append(LogHeader.getText());
    }

    void save(LogLevel lvl, String tag, String content) {
        String time = "["+getTime()+"]";
        String priority = "[("+lvl.ID+") "+lvl.NAME+"]";
        String data;
        if(tag.length()==0) {
            data = time+priority+": "+content;
        } else {
            tag = "["+tag+"]";
            data = time+priority+tag+": "+content;
        }
        append(data);
    }

    private static String getTime() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS").format(new Date());
    }

    private void append(String data) {
        try {
            File file = getLogFile();
            if(!file.exists()) {
                file.createNewFile();
            }
            data = ENTER+data;
            Files.write(file.toPath(), data.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getLogFile() {
        return FILE.getDest();
    }
}
