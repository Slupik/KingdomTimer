package jw.kingdom.hall.kingdomtimer.view.viewer;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorEventHandler;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorObservableList;
import jw.kingdom.hall.kingdomtimer.view.utils.view.StageWindow;
import jw.kingdom.hall.kingdomtimer.view.utils.view.WindowSettings;
import jw.kingdom.hall.kingdomtimer.view.utils.view.Screens;
import jw.kingdom.hall.kingdomtimer.view.utils.view.WindowController;

import java.awt.*;

/**
 * All rights reserved & copyright ©
 */
public class ViewerWindow implements StageWindow {
    public final WindowController CONTROLLER;
    private static Monitor actualDevice;

    private static Stage stage;
    private static Scene scene;
    private static Group root;

    public ViewerWindow() {
        CONTROLLER = new WindowController(this);
    }

    public void build(Stage primaryStage){
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);
        root = new Group();

        stage.setTitle(WindowSettings.TITLE);

        loadScreens();
        root.getChildren().addAll(CONTROLLER.getScreensPane());
        CONTROLLER.setScreen(Screens.VIEWER);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setAlwaysOnTop(true);
        stage.show();

        stage.setOnCloseRequest(event -> System.exit(0));

        MonitorManager.addListener(new MonitorEventHandler() {
            @Override
            public void onPlugIn(GraphicsDevice device) {
                Monitor monitorIn = new Monitor(device);
                if(actualDevice == null || monitorIn.ID.equals(actualDevice.ID)){
                    setMonitor(monitorIn);
                } else {
                    autoSelectScreen();
                }
            }

            @Override
            public void onPlugOut(GraphicsDevice device) {
                Monitor monitorOut = new Monitor(device);
                if(actualDevice == null || monitorOut.ID.equals(actualDevice.ID)){
                    Platform.runLater(()->{
                        if(getStage().isShowing()){
                            getStage().hide();
                        }
                    });
                } else {
                    autoSelectScreen();
                }
            }
        });
        autoSelectScreen();
    }

    public void loadScreens() {
        CONTROLLER.loadScreen(Screens.VIEWER);
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    private void autoSelectScreen(){
        MonitorObservableList list = MonitorManager.monitors;

        if(list.size() < 2){
            setMonitor(null);
        } else {
            for(int i=list.size()-1;i>=0;i--){
                Monitor monitor = list.get(i);
                if(!monitor.isMain()){
                    setMonitor(monitor);
                    return;
                }
            }
            setMonitor(null);
        }
    }

    private void setMonitor(Monitor monitor) {
        Platform.runLater(()->{
            actualDevice = monitor;
            if(monitor==null){
                getStage().hide();
                return;
            }

            if(!getStage().isShowing()){
                getStage().show();
            }

            getStage().setWidth(monitor.getDisplayMode().getWidth());
            getStage().setHeight(monitor.getDisplayMode().getHeight());

            getStage().setX(
                    monitor.getDefaultConfiguration().getBounds().getX()
            );
            getStage().setY(
                    monitor.getDefaultConfiguration().getBounds().getY()
            );
//            getStage().getScene().wid
        });
    }
}
