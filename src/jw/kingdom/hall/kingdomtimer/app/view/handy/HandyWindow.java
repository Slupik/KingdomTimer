package jw.kingdom.hall.kingdomtimer.app.view.handy;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.app.view.loader.Screens;
import jw.kingdom.hall.kingdomtimer.app.view.loader.StageWindow;
import jw.kingdom.hall.kingdomtimer.app.view.loader.WindowController;
import jw.kingdom.hall.kingdomtimer.javafx.window.WindowSettings;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
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
        stage.initStyle(StageStyle.TRANSPARENT);
        root = new Group();

        stage.setTitle(WindowSettings.TITLE);
        stage.setAlwaysOnTop(true);

        loadScreens();
        root.getChildren().addAll(CONTROLLER.getScreensPane());
        CONTROLLER.setScreen(Screens.HANDY_PANEL);

        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        stage.setWidth(380);
        stage.setHeight(110);

        new WindowMovingController(stage, root);

        new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->{
                getStage().sizeToScene();
            });
        }).start();
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
