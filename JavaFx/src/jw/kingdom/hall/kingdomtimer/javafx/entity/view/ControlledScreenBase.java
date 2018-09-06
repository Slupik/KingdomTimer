package jw.kingdom.hall.kingdomtimer.javafx.entity.view;

import jw.kingdom.hall.kingdomtimer.javafx.loader.ViewManager;

/**
 * All rights reserved & copyright ©
 */
public abstract class ControlledScreenBase implements ControlledScreen {
    private AppWindow window;
    private ViewManager viewManager;

    @Override
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @Override
    public void setWindow(AppWindow window) {
        this.window = window;
    }
}
