package jw.kingdom.hall.kingdomtimer.data.config;

import java.io.IOException;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class AppConfigTest {

    /*
    Only for tests purposes.
     */
    public static void main(String[] args) {
        AppConfig cfg = AppConfig.getInstance();
        System.out.println(cfg.getSpeakerScreen());
        cfg.setSpeakerScreen("test screen");
        try {
            cfg.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(cfg.getSpeakerScreen());
//        cfg.
    }
}
