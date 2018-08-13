package jw.kingdom.hall.kingdomtimer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.view.panel.PanelWindow;
import jw.kingdom.hall.kingdomtimer.view.viewer.ViewerController;
import jw.kingdom.hall.kingdomtimer.view.viewer.ViewerWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MonitorManager.initialize();
        new PanelWindow().build(primaryStage);
        new ViewerWindow().build(new Stage());

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
