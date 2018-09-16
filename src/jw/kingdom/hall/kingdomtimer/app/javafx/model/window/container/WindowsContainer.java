package jw.kingdom.hall.kingdomtimer.app.javafx.model.window.container;

import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowType;

/**
 * All rights reserved & copyright ©
 */
public interface WindowsContainer {
    void putAppWindow(WindowType type, AppWindow window);

    @Nullable
    AppWindow getAppWindow(WindowType type);
}
