package jw.kingdom.hall.kingdomtimer.javafx.entity.view.screen;

import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.container.WindowsContainer;
import jw.kingdom.hall.kingdomtimer.javafx.loader.ViewManager;

/**
 * All rights reserved & copyright ©
 */
public interface ControlledScreen {

    void setWindow(AppWindow window);

    void setViewManager(ViewManager viewManager);

    void setWindowsContainer(WindowsContainer container);
    WindowsContainer getWindowsContainer();

    void setWindowData(WindowInput input);
    WindowInput getWindowData();
}
