package jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.device.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorEventHandler;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorManager;
import jw.kingdom.hall.kingdomtimer.device.monitor.MonitorObservableList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker.SpeakerWindow.Screens.MAIN;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class SpeakerWindow extends AppWindow {

    public static final boolean DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR = false;

    private static Monitor actualDevice;

    public void loadScreens() {
        viewManager.loadScreen(MAIN.name, MAIN.path);
    }

    @Override
    protected void setMainView() {
        scene.setRoot((Parent) viewManager.getScreen(MAIN.name));

        //Standard way to set view. It shouldn't be used until somebody will repair bug with additional blank space (~15px left margin)
//        viewManager.setScreen(MAIN.name);
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
        stage.setMaximized(true);
        if(!DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR) {
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setAlwaysOnTop(true);
        } else {
            stage.setMaximized(true);
        }
        stage.setScene(scene);
    }

    @Override
    protected void onPostShow() {
        super.onPostShow();
        stage.setOnCloseRequest(event -> System.exit(0));
        stage.show();

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
        setVisibility(getConfig().isVisibleSpeakerScreen());
        runThreadPreventingByMainMonitor();
    }

    private void runThreadPreventingByMainMonitor() {
        new Thread(new Runnable() {
            private boolean errorWithMainScreen = false;
            @Override
            public void run() {
                if(DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR) {
                    return;
                }
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
                            Platform.runLater(stage::hide);
                        } else {
                            errorWithMainScreen = false;
                            Platform.runLater(()-> setVisibility(getConfig().isVisibleSpeakerScreen()));
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

    private void autoSelectScreen(){
        MonitorObservableList list = MonitorManager.monitors;

        String fromConfig = getConfig().getSpeakerScreen();
        if(fromConfig==null || fromConfig.length()<1) {
            if(list.size() < 2 && !DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR){
                setMonitor(null);
            } else {
                for(int i=list.size()-1;i>=0;i--){
                    Monitor monitor = list.get(i);
                    if(DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR || !monitor.isMain()){
                        setMonitor(monitor);
                        return;
                    }
                }
                setMonitor(null);
            }
        } else {
            for(int i=list.size()-1;i>=0;i--){
                Monitor monitor = list.get(i);
                if(monitor.getIDstring().equals(fromConfig) && (!monitor.isMain() || DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR)){
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

            setVisibility(getConfig().isVisibleSpeakerScreen());
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

    public enum Screens {
        MAIN("main", "/layout/window/speaker/main.fxml"),
        ;

        public final String name;
        public final String path;

        Screens(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
