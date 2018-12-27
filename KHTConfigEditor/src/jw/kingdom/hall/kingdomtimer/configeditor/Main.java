package jw.kingdom.hall.kingdomtimer.configeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jw.kingdom.hall.kingdomtimer.configeditor.view.MainViewController;

/**
 * All rights reserved & copyright ©
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main.fxml"));
        Parent root = loader.load();
        MainViewController controller = loader.getController();

        primaryStage.setTitle("KHT Config Editor");
        primaryStage.setScene(new Scene(root, 900, 556));
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(556);
        primaryStage.show();
        primaryStage.sizeToScene();
    }
}
