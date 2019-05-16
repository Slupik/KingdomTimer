package jw.kingdom.hall.kingdomtimer.config.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
//TODO merge with FileUtils from domain
public class FileUtils {
    public static String getContent(File file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder content = new StringBuilder();
        for(String line:lines) {
            content.append(line);
        }
        return content.toString();
    }

    public static void writeToFile(File file, String content) {
        try {
            OutputStreamWriter writer =
                    new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
