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
import jw.kingdom.hall.kingdomtimer.app.view.loader.WindowSettings;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorObservableList;

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

        Platform.runLater(()-> setPosToRightUp());

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

    private void setPosToRightUp() {
        Monitor monitor = getMainMonitor();
        if(monitor!=null) {
            getStage().setX(
                    monitor.getDefaultConfiguration().getBounds().getWidth()-getStage().getWidth()
            );
            getStage().setY(
                    monitor.getDefaultConfiguration().getBounds().getY()
            );
        }
    }

    private Monitor getMainMonitor() {
        MonitorObservableList list = MonitorManager.monitors;
        for(int i=list.size()-1;i>=0;i--){
            Monitor monitor = list.get(i);
            if(monitor.isMain()){
                return monitor;
            }
        }
        return null;
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
