
package jw.kingdom.hall.kingdomtimer.webui.view.panel;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.webui.domain.app.AppWindow;
import jw.kingdom.hall.kingdomtimer.webui.domain.di.WindowInput;

import static jw.kingdom.hall.kingdomtimer.webui.view.panel.WindowPanel.Screens.MAIN;

public class WindowPanel extends AppWindow {

    public WindowPanel(Stage stage, WindowInput input) {
        super(stage, input);
    }

    @Override
    protected String getTitle() {
        return "Panel sterowania";
    }

    @Override
    protected boolean isToShowAtInit() {
        return true;
    }

    @Override
    protected void loadScreens() {
        viewManager.loadScreen(MAIN.name, MAIN.path);
    }

    @Override
    protected void setMainView() {
        viewManager.setScreen(MAIN.name);
    }

    public enum Screens {
        MAIN("main", "/layout/window/panel/main.fxml"),
        ;

        public final String name;
        public final String path;

        Screens(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
