package jw.kingdom.hall.kingdomtimer.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.javafx.entity.bussines.BackupController;
import jw.kingdom.hall.kingdomtimer.javafx.window.speaker.SpeakerWindow;

/**
 * All rights reserved & copyright ©
 */
public class App extends Application {

    private final BackupController backup;

    public App(){
        backup = null;
    }

    public App(BackupController backup) {
        this.backup = backup;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new SpeakerWindow(primaryStage, backup).init();
    }
}
