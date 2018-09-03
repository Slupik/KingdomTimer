package jw.kingdom.hall.kingdomtimer.domain.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class FileUtils {
    public static String getContent(File file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder config = new StringBuilder();
        for(String line:lines) {
            config.append(line);
        }
        return config.toString();
    }
}
