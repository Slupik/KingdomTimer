package jw.kingdom.hall.kingdomtimer.app;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.domain.multimedia.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.device.local.AutoRAMCleaner;
import jw.kingdom.hall.kingdomtimer.app.view.panel.PanelWindow;
import jw.kingdom.hall.kingdomtimer.app.view.viewer.ViewerWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MonitorManager.initialize();
        new PanelWindow().build(primaryStage);
        ViewerWindow.getInstance().build(new Stage());

        MultimediaPreviewer.getInstance().setPause(false);
        AutoRAMCleaner.run();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
