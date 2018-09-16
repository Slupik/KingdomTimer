package jw.kingdom.hall.kingdomtimer.app.javafx.model.screen;

import jw.kingdom.hall.kingdomtimer.app.javafx.loader.ViewManager;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.container.WindowsContainer;

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
