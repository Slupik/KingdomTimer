package jw.kingdom.hall.kingdomtimer.domain.time.countdown.gleam;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class GlobalGleamController {
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    private static GlobalGleamController instance;
    public static GlobalGleamController getInstance() {
        if(instance==null) {
            instance = new GlobalGleamController();
        }
        return instance;
    }
    private GlobalGleamController(){}
}
