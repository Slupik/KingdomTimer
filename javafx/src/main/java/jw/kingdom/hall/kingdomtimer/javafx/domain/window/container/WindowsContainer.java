package jw.kingdom.hall.kingdomtimer.javafx.domain.window.container;

import org.jetbrains.annotations.Nullable;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.domain.window.WindowType;

/**
 * All rights reserved & copyright ©
 */
public interface WindowsContainer {
    void putAppWindow(WindowType type, AppWindow window);

    @Nullable
    AppWindow getAppWindow(WindowType type);
}
