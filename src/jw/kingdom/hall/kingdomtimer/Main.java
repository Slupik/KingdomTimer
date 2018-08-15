package jw.kingdom.hall.kingdomtimer;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.model.MultimediaPreviewer;
import jw.kingdom.hall.kingdomtimer.view.panel.PanelWindow;
import jw.kingdom.hall.kingdomtimer.view.viewer.ViewerWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MonitorManager.initialize();
        new PanelWindow().build(primaryStage);
        ViewerWindow.getInstance().build(new Stage());

        MultimediaPreviewer.getInstance().setPause(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
