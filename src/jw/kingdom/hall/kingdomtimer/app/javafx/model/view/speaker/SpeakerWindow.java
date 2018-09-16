package jw.kingdom.hall.kingdomtimer.app.javafx.model.view.speaker;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.model.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.data.config.AppConfig;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorEventHandler;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorObservableList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static jw.kingdom.hall.kingdomtimer.app.javafx.model.view.widget.HandyWindow.Screens.MAIN;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpeakerWindow extends AppWindow {
    private static Monitor actualDevice;

    public void loadScreens() {
        viewManager.loadScreen(MAIN.name, MAIN.path);
    }

    @Override
    protected void setMainView() {
        viewManager.setScreen(MAIN.name);
    }

    public SpeakerWindow(Stage stage, WindowInput input) {
        super(stage, input);
    }

    @Override
    protected boolean isToShowAtInit() {
        return false;
    }

    @Override
    protected void onPreInit() {
        super.onPreInit();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setAlwaysOnTop(true);
    }

    @Override
    protected void onPostShow() {
        super.onPostShow();
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

        MonitorManager.monitors.addListener((ListChangeListener<Monitor>) c -> {
            if(actualDevice==null) {
                autoSelectScreen();
            }
        });
        autoSelectScreen();
        setVisibility(AppConfig.getInstance().isVisibleSpeakerScreen());
        runThreadPreventingByMainMonitor();
    }

    private void runThreadPreventingByMainMonitor() {
        new Thread(new Runnable() {
            private boolean errorWithMainScreen = false;
            @Override
            public void run() {
                while (stage!=null) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Monitor monitor = getMainMonitor();
                    if(monitor!=null && !errorWithMainScreen) {
                        if(stage.getX()==monitor.getDefaultConfiguration().getBounds().getX() &&
                                stage.getY()==monitor.getDefaultConfiguration().getBounds().getY()) {
                            errorWithMainScreen = true;
                            Platform.runLater(()-> stage.hide());
                        } else {
                            errorWithMainScreen = false;
                            Platform.runLater(()-> setVisibility(AppConfig.getInstance().isVisibleSpeakerScreen()));
                        }
                    }
                }
            }
        }).start();
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

        String fromConfig = AppConfig.getInstance().getSpeakerScreen();
        if(fromConfig==null || fromConfig.length()<1) {
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
        } else {
            for(int i=list.size()-1;i>=0;i--){
                Monitor monitor = list.get(i);
                if(monitor.getIDstring().equals(fromConfig) && !monitor.isMain()){
                    setMonitor(monitor);
                    return;
                }
            }
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
