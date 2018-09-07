package jw.kingdom.hall.kingdomtimer.javafx.view.speaker;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.window.WindowInput;

import static jw.kingdom.hall.kingdomtimer.javafx.view.speaker.SpeakerWindow.Screens.MAIN;

/**
 * All rights reserved & copyright ©
 */
public class SpeakerWindow extends AppWindow {

    public SpeakerWindow(Stage stage, WindowInput input) {
        super(stage, input);
    }

    @Override
    protected void loadScreens() {
        viewManager.loadScreen(MAIN.name, MAIN.path);
    }

    @Override
    protected void setMainView() {
        viewManager.setScreen(MAIN.name);
    }

    @Override
    protected void onPreInit() {

    }

    @Override
    protected void onPreShow() {

    }

    @Override
    protected void onPostShow() {

    }

    public enum Screens {
        MAIN("main", "/layout/window/speaker/main.fxml"),
        ;

        public final String name;
        public final String path;

        Screens(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
