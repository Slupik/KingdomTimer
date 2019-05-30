package jw.kingdom.hall.kingdomtimer.webui.domain;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.webui.domain.AppInput;

public class App {

    private final AppInput appInput;

    public App(AppInput appInput) {
        this.appInput = appInput;
    }

    public void start(Stage primaryStage) throws Exception {
        initWindows(primaryStage);
    }

    private void initWindows(Stage primaryStage) {

    }

}
