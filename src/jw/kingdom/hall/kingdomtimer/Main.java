package jw.kingdom.hall.kingdomtimer;

import javafx.application.Application;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.view.panel.PanelWindow;
import jw.kingdom.hall.kingdomtimer.view.viewer.ViewerWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MonitorManager.initialize();
        new PanelWindow().build(primaryStage);
        new ViewerWindow().build(new Stage());
    }


    public static void main(String[] args) {
        launch(args);
    }
}