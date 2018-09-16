package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.head.tab;


import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.container.WindowsContainer;

/**
 * All rights reserved & copyright ©
 */
public interface TabPresentable {

    void setWindowsContainer(WindowsContainer windowsContainer);
    WindowsContainer getWindowsContainer();

    void setWindowData(WindowInput input);
    WindowInput getWindowData();
}
