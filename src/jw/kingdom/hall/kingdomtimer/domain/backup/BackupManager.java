package jw.kingdom.hall.kingdomtimer.domain.backup;

import com.google.gson.Gson;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.OfflineMeetingBean;
import jw.kingdom.hall.kingdomtimer.domain.backup.entity.TimeBackupBean;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BackupManager {
    private static boolean running = false;

    private static TimeBackupMaker time;

    public static void restore(){
        String content = getBackupContent();
        if(content.length()!=0) {
            TimeBackupBean bean = new Gson().fromJson(content, TimeBackupBean.class);
            TimeBackupRestorer.restore(bean);
        }
    }

    @NotNull
    private static String getBackupContent() {
        try {
            List<String> lines = Files.readAllLines(FileManager.getScheduleFile().toPath(), Charset.forName("UTF-8"));
            StringBuilder content = new StringBuilder();
            for(String line:lines) {
                content.append('\n').append(line);
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void start(){
        if(!running) {
            running = true;
        } else {
            return;
        }
        init();
    }

    private static void init() {
        time = new TimeBackupMaker();
    }
}
