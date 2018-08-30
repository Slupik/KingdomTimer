package jw.kingdom.hall.kingdomtimer.app.view.handy;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.app.view.loader.Screens;
import jw.kingdom.hall.kingdomtimer.app.view.loader.StageWindow;
import jw.kingdom.hall.kingdomtimer.app.view.loader.WindowController;
import jw.kingdom.hall.kingdomtimer.app.view.loader.WindowSettings;
import jw.kingdom.hall.kingdomtimer.app.view.viewer.ViewerWindow;

/**
 * All rights reserved & copyright ©
 */
public class HandyWindow implements StageWindow {
    private static HandyWindow instance;
    public final WindowController CONTROLLER;

    private static Stage stage;
    private static Scene scene;
    private static Group root;

    private HandyWindow() {
        CONTROLLER = new WindowController(this);
    }

    public void build(Stage primaryStage){
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);
        root = new Group();

        stage.setTitle(WindowSettings.TITLE);
        stage.setAlwaysOnTop(true);

        loadScreens();
        root.getChildren().addAll(CONTROLLER.getScreensPane());
        CONTROLLER.setScreen(Screens.HANDY_PANEL);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        stage.setWidth(400);
        stage.setHeight(100);
        new WindowMovingController(stage, root);
    }

    public void loadScreens() {
        CONTROLLER.loadScreen(Screens.HANDY_PANEL);
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public static HandyWindow getInstance(){
        if(null == instance) {
            instance = new HandyWindow();
        }
        return instance;
    }
}
