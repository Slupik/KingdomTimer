package jw.kingdom.hall.kingdomtimer.view.common;

import jw.kingdom.hall.kingdomtimer.view.utils.ControlledScreen;
import jw.kingdom.hall.kingdomtimer.view.utils.ScreensController;

/**
 * All rights reserved & copyright ©
 */
public abstract class ControlledScreenImpl implements ControlledScreen {

    private ScreensController myController;
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController=screenPage;
    }
}
