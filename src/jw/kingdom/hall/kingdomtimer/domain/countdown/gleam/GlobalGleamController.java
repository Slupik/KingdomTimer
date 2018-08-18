package jw.kingdom.hall.kingdomtimer.domain.countdown.gleam;

/**
 * All rights reserved & copyright ©
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
