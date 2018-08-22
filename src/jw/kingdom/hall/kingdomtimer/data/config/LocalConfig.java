package jw.kingdom.hall.kingdomtimer.data.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jw.kingdom.hall.kingdomtimer.data.config.structure.ConfigRoot;
import jw.kingdom.hall.kingdomtimer.domain.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
class LocalConfig {
    private ConfigRoot parsed;

    String getDefaultSpeakerScreen() {
//        parsed.getSpeaker()
        return "";
    }

    private static LocalConfig instance;
    static LocalConfig getInstance(){
        if(null == instance) {
            instance = new LocalConfig();
        }
        return instance;
    }
    private LocalConfig(){
        File file = new File("Config.json");
        String content = FileUtils.getContent(file);
        parsed = new Gson().fromJson(content, ConfigRoot.class);
    }
}
