package jw.kingdom.hall.kingdomtimer.javafx.view.head.tab.time.timedirect;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.config.model.Config;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForPanel extends BtnTimeDirectBase {
    private boolean isDirectDown;
    private Config config;

    public BtnTimeDirectForPanel(Button button, Config config) {
        super(button);
        this.config = config;
        isDirectDown = getConfig().isDirectDown();
        init();
    }

    @Override
    protected void changeDirect() {
        setDirectDown(!isDirectDown());
    }

    @Override
    public boolean isDirectDown() {
        return isDirectDown;
    }

    public void setDirectDown(boolean isDirectDown) {
        this.isDirectDown = isDirectDown;
        updateImage();
        notifyCountdownDirectChange(isDirectDown);
    }

    public void reset() {
        setDirectDown(getConfig().isDirectDown());
    }

    private Config getConfig() {
        return config;
    }
}
