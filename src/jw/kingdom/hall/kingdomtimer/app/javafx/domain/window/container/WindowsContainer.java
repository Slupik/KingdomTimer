package jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.container;

import com.sun.istack.internal.Nullable;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowType;

/**
 * All rights reserved & copyright ©
 */
public interface WindowsContainer {
    void putAppWindow(WindowType type, AppWindow window);

    @Nullable
    AppWindow getAppWindow(WindowType type);
}
