package jw.kingdom.hall.kingdomtimer.webui.domain.app;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.webui.domain.di.WindowInput;
import jw.kingdom.hall.kingdomtimer.webui.view.panel.WindowPanel;

public class App {

    private final AppInput appInput;

    public App(AppInput appInput) {
        this.appInput = appInput;
    }

    public void start(Stage primaryStage) throws Exception {
        initWindows(primaryStage);
    }

    private void initWindows(Stage primaryStage) {
        AppWindow window = new WindowPanel(primaryStage, getWindowInput());
        window.init();
    }

    private WindowInput getWindowInput() {
        return new WindowInput() {
            //TODO implement

            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

}
