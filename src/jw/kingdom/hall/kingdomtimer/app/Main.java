package jw.kingdom.hall.kingdomtimer.app;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.app.App;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.app.AppInput;
import jw.kingdom.hall.kingdomtimer.config.model.Config;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.data.log.DefaultLogFile;
import jw.kingdom.hall.kingdomtimer.device.local.AutoRAMCleaner;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.RecordControl;
import jw.kingdom.hall.kingdomtimer.domain.record.voice.VoiceRecorder;
import jw.kingdom.hall.kingdomtimer.log.Log;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Log.init(new DefaultLogFile());
        AppConfig.init();
        RecordControl recordControl = VoiceRecorder.getInstance();
        BackupManager.start(recordControl);
        MonitorManager.initialize();
        try {
            new App(new AppInput() {
                @Override
                public Config getConfig() {
                    return AppConfig.getInstance();
                }

                @Override
                public RecordControl getRecorder() {
                    return recordControl;
                }
            }).start(primaryStage);
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
