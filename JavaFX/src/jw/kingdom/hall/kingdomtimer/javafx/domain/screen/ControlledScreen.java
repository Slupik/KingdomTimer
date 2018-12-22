package jw.kingdom.hall.kingdomtimer.javafx.domain.screen;

import jw.kingdom.hall.kingdomtimer.javafx.domain.loader.ViewManager;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.container.WindowsContainer;

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
