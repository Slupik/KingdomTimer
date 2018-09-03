package jw.kingdom.hall.kingdomtimer.app.view.viewer;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorEventHandler;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorObservableList;
import jw.kingdom.hall.kingdomtimer.app.view.loader.StageWindow;
import jw.kingdom.hall.kingdomtimer.app.view.loader.WindowSettings;
import jw.kingdom.hall.kingdomtimer.app.view.loader.Screens;
import jw.kingdom.hall.kingdomtimer.app.view.loader.WindowController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class ViewerWindow implements StageWindow {
    private static ViewerWindow instance;
    public final WindowController CONTROLLER;
    private static Monitor actualDevice;

    private static Stage stage;
    private static Scene scene;
    private static Group root;

    private ViewerWindow() {
        CONTROLLER = new WindowController(this);
    }
    public static ViewerWindow getInstance(){
        if(null == instance) {
            instance = new ViewerWindow();
        }
        return instance;
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
        setVisibility(AppConfig.getInstance().isVisibleSpeakerScreen());
    }

    public void loadScreens() {
        CONTROLLER.loadScreen(Screens.VIEWER);
    }

    public boolean setVisibility(boolean isVisible) {
        if(actualDevice!=null)  {
            if(isVisible) {
                getStage().show();
            } else {
                getStage().hide();
            }
            return true;
        }
        return false;
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

    public void setMonitor(Monitor monitor) {
        actualDevice = monitor;
        notifyListeners(monitor);
        Platform.runLater(()->{
            if(monitor==null){
                getStage().hide();
                return;
            }

            setVisibility(AppConfig.getInstance().isVisibleSpeakerScreen());
            stage.setMaximized(false);

            getStage().setWidth(monitor.getDisplayMode().getWidth());
            getStage().setHeight(monitor.getDisplayMode().getHeight());

            getStage().setX(
                    monitor.getDefaultConfiguration().getBounds().getX()
            );
            getStage().setY(
                    monitor.getDefaultConfiguration().getBounds().getY()
            );
            stage.setMaximized(true);
        });
    }

    public Monitor getMonitor() {
        return actualDevice;
    }

    /*
    Listeners
     */
    private List<Listener> monitorChangeListenerList = new ArrayList<>();
    public void addOnMonitorChangeListener(Listener listener) {
        monitorChangeListenerList.add(listener);
    }
    public void removeOnMonitorChangeListener(Listener listener) {
        for(Listener value:monitorChangeListenerList) {
            if(value.getId().equals(listener.getId())) {
                monitorChangeListenerList.remove(value);
            }
        }
    }

    private void notifyListeners(Monitor monitor) {
        for(Listener listener:monitorChangeListenerList) {
            listener.onMonitorChange(monitor);
        }
    }

    public interface Listener {
        void onMonitorChange(Monitor monitor);
        String getId();
    }
}
