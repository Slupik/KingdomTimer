package jw.kingdom.hall.kingdomtimer.app;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.app.view.handy.HandyWindow;
import jw.kingdom.hall.kingdomtimer.app.view.panel.PanelWindow;
import jw.kingdom.hall.kingdomtimer.app.view.viewer.ViewerWindow;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.data.log.DefaultLogFile;
import jw.kingdom.hall.kingdomtimer.device.local.AutoRAMCleaner;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.domain.backup.BackupManager;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.log.Log;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Log.init(new DefaultLogFile());
        AppConfig.init();
        BackupManager.start();
        MonitorManager.initialize();
        new PanelWindow().build(primaryStage);
        HandyWindow.getInstance().build(new Stage());
        ViewerWindow.getInstance().build(new Stage());

        MultimediaPreviewer.getInstance().setPause(false);
        AutoRAMCleaner.run();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
