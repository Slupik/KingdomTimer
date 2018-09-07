package jw.kingdom.hall.kingdomtimer.javafx.control.time.direct;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.config.model.Config;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForPanel extends BtnTimeDirectBase {
    private final Config config;
    private boolean isDirectDown;

    public BtnTimeDirectForPanel(Config config, Button button) {
        super(button);
        this.config = config;
        isDirectDown = config.isDirectDown();
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
        setDirectDown(config.isDirectDown());
    }
}
