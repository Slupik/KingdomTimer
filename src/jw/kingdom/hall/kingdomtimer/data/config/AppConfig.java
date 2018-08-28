package jw.kingdom.hall.kingdomtimer.data.config;

import jw.kingdom.hall.kingdomtimer.config.json.JsonConfig;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.model.ConfigWriteable;
import jw.kingdom.hall.kingdomtimer.config.utils.DefaultConfig;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * All rights reserved & copyright ©
 */
public class AppConfig extends ConfigBase {
    private ConfigWriteable config;

    public void save() throws IOException {
        if(ConfigFiles.getLocal().exists()) {
            save(ConfigFiles.getLocal());
        } else if(ConfigFiles.getUser().exists()) {
            save(ConfigFiles.getUser());
        } else {
            save(ConfigFiles.getLocal());
        }
    }

    @Override
    protected Config getConfig() {
        return config;
    }

    private AppConfig(){
        ConfigWriteable local = readConfig(ConfigFiles.getLocal());
        ConfigWriteable user = readConfig(ConfigFiles.getUser());
        ConfigWriteable global = readConfig(ConfigFiles.getGlobal());
        config = getUnifiedConfig(local, user, global);
    }

    @NotNull
    private ConfigWriteable getUnifiedConfig(ConfigWriteable... configs) {
        ConfigWriteable root = null;

        for(int i=configs.length-1;i>=0;i--) {
            ConfigWriteable config = configs[i];
            if(root != null && config!=null) {
                config.loadParent(root);
                root = config;
            } else if(config != null) {
                root = config;
            }
        }

        if(root==null) {
            root = new DefaultConfig();
        }
        return root;
    }

    private ConfigWriteable readConfig(File source) {
        if(source!=null && source.exists()) {
            try {
                String content = getFileContent(source);
                ConfigWriteable cfg = new JsonConfig();
                cfg.loadLocalData(content);
                return cfg;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String getFileContent(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

    private static AppConfig instance;
    public static AppConfig getInstance() {
        if(instance==null) {
            instance = new AppConfig();
        }
        return instance;
    }
}
