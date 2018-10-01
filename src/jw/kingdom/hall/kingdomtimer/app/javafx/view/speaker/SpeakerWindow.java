package jw.kingdom.hall.kingdomtimer.app.javafx.view.speaker;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.AppWindow;
import jw.kingdom.hall.kingdomtimer.app.javafx.domain.window.WindowInput;
import jw.kingdom.hall.kingdomtimer.domain.monitor.Monitor;
import jw.kingdom.hall.kingdomtimer.domain.monitor.MonitorEventHandler;

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
        return DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR;
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

        getInput().getMonitorsManager().addListener(new MonitorEventHandler() {
            @Override
            public void onPlugIn(Monitor device) {
                if(actualDevice == null || device.getId().equals(actualDevice.getId())){
                    setMonitor(device);
                } else {
                    autoSelectScreen();
                }
            }

            @Override
            public void onPlugOut(Monitor device) {
                if(actualDevice == null || device.getId().equals(actualDevice.getId())){
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
                        if(stage.getX()==monitor.getX() &&
                                stage.getY()==monitor.getY()) {
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
        List<Monitor> list = getInput().getMonitorsManager().getAll();
        for(int i=list.size()-1;i>=0;i--){
            Monitor monitor = list.get(i);
            if(monitor.isPrimary()){
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
        List<Monitor> list = getInput().getMonitorsManager().getAll();

        String fromConfig = getConfig().getSpeakerScreen();
        if(fromConfig==null || fromConfig.length()<1) {
            if(list.size() < 2 && !DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR){
                setMonitor(null);
            } else {
                for(int i=list.size()-1;i>=0;i--){
                    Monitor monitor = list.get(i);
                    if(DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR || !monitor.isPrimary()){
                        setMonitor(monitor);
                        return;
                    }
                }
                setMonitor(null);
            }
        } else {
            for(int i=list.size()-1;i>=0;i--){
                Monitor monitor = list.get(i);
                if(monitor.getId().equals(fromConfig) && (!monitor.isPrimary() || DEBUGGING_FORCE_SHOW_ON_SINGLE_MONITOR)){
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

            getStage().setWidth(monitor.getWidth());
            getStage().setHeight(monitor.getHeight());

            getStage().setX(
                    monitor.getX()
            );
            getStage().setY(
                    monitor.getY()
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
