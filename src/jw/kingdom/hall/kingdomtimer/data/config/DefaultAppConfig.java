package jw.kingdom.hall.kingdomtimer.data.config;

import jw.kingdom.hall.kingdomtimer.config.json.JsonConfig;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.config.model.ConfigWriteable;
import jw.kingdom.hall.kingdomtimer.config.utils.DefaultConfig;
import jw.kingdom.hall.kingdomtimer.config.utils.FileUtils;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class DefaultAppConfig extends ConfigBase implements AppConfig {
    private ConfigWriteable config;

    @Override
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

    private DefaultAppConfig(){
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
            String content = FileUtils.getContent(source);
            ConfigWriteable cfg = new JsonConfig();
            cfg.loadLocalData(content);
            return cfg;
        }
        return null;
    }

    private static DefaultAppConfig instance;
    public static DefaultAppConfig getInstance() {
        init();
        return instance;
    }

    private static void init() {
        if(instance==null) {
            instance = new DefaultAppConfig();
        }
    }

    @Override
    public void setSpeakerScreen(Monitor monitor) {
        super.setSpeakerScreen(monitor.getId());
    }

    @Override
    public void setMultimediaScreen(Monitor monitor) {
        super.setMultimediaScreen(monitor.getId());
    }
}
