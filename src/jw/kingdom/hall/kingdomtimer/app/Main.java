package jw.kingdom.hall.kingdomtimer.app;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.App;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.AppInput;
import jw.kingdom.hall.kingdomtimer.app.view.panel.PanelWindow;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.data.log.DefaultLogFile;
import jw.kingdom.hall.kingdomtimer.device.local.AutoRAMCleaner;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.log.Log;
import jw.kingdom.hall.kingdomtimer.recorder.Recorder;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Log.init(new DefaultLogFile());
        AppConfig.init();
        BackupManager.start();
        MonitorManager.initialize();
        new PanelWindow().build(primaryStage);
//        HandyWindow.getInstance().build(new Stage());
//        SpeakerWindow.getInstance().build(new Stage());
        try {
            new App(new AppInput() {
                @Override
                public Config getConfig() {
                    return AppConfig.getInstance();
                }

                @Override
                public Recorder getRecorder() {
                    return null;
                }
            }).start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MultimediaPreviewer.getInstance().setPause(false);
        AutoRAMCleaner.run();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
