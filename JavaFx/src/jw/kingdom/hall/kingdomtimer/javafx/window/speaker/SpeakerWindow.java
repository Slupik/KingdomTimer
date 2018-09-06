package jw.kingdom.hall.kingdomtimer.javafx.window.speaker;

import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.javafx.entity.bussines.BackupController;
import jw.kingdom.hall.kingdomtimer.javafx.entity.view.AppWindow;

import static jw.kingdom.hall.kingdomtimer.javafx.window.speaker.SpeakerWindow.Screens.*;

/**
 * All rights reserved & copyright ©
 */
public class SpeakerWindow extends AppWindow {

    private final BackupController backup;

    public SpeakerWindow(Stage stage, BackupController backup) {
        super(stage);
        this.backup = backup;
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
        stage.setOnCloseRequest(event -> {
            backup.delete();
            System.exit(0);
        });
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
