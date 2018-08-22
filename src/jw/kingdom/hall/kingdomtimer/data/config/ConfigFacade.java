package jw.kingdom.hall.kingdomtimer.data.config;

/**
 * All rights reserved & copyright ©
 */
public class ConfigFacade {

    public static String getDefaultSpeakerScreen(){
//        getLocal().getDefaultSpeakerScreen().;
        return "";
    }

    private static LocalConfig getLocal(){
        return LocalConfig.getInstance();
    }
}
