package jw.kingdom.hall.kingdomtimer.config;

import jw.kingdom.hall.kingdomtimer.config.json.JsonConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * All rights reserved & copyright ©
 */
public class ConfigTest {
    public static void main(String[] args) {
        File dest = new File("test.json");

        Config config = new JsonConfig();
        config.fillWith(ConfigStatic.DEFAULT);
        try {
            config.save(dest);

            config.setMinRefreshRate(33);
            config.save(dest);

            Config secondConfig = new JsonConfig();
            secondConfig.loadLocalData(getFileContent(dest));
            System.out.println("secondConfig.getMinRefreshRate() = " + secondConfig.getMinRefreshRate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileContent(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
