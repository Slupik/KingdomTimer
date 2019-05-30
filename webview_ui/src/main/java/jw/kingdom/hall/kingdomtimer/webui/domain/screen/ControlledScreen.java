package jw.kingdom.hall.kingdomtimer.webui.domain.screen;


import jw.kingdom.hall.kingdomtimer.webui.domain.app.AppWindow;
import jw.kingdom.hall.kingdomtimer.webui.domain.di.WindowInput;
import jw.kingdom.hall.kingdomtimer.webui.domain.loader.ViewManager;

public interface ControlledScreen {

    void setWindow(AppWindow window);

    void setViewManager(ViewManager viewManager);

    void setWindowData(WindowInput input);
}
