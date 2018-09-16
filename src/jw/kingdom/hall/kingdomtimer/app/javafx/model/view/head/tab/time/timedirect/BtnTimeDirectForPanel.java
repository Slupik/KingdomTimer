package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.head.tab.time.timedirect;

import javafx.scene.control.Button;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class BtnTimeDirectForPanel extends BtnTimeDirectBase {
    private boolean isDirectDown = AppConfig.getInstance().isDirectDown();

    public BtnTimeDirectForPanel(Button button) {
        super(button);
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
        setDirectDown(AppConfig.getInstance().isDirectDown());
    }
}
